package id.cavannus.thetaleofwayang.classifier

import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import org.tensorflow.lite.Interpreter
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import java.util.*

class ImageClassifier(assetManager: AssetManager, modelPath: String, labelPath: String, inputSize: Int, inputSize2: Int) {
    private val labels: List<String>
    private val model: Interpreter
    private var interpreter: Interpreter
    private var labellist: List<String>

    private val inputsize: Int = inputSize
    private val inputsize2: Int = inputSize2
    private val pixelsize: Int = 3
    private val imagemean = 0
    private val imagestd = 255.0f
    private val maxresults = 3
    private val threshold = 0.4f

    data class Recognition(
        var id: String = "",
        var title: String = "",
        var confidence: Float = 0F,
        val percent: Float = confidence*100
    )  {
        override fun toString(): String {
            return "Title = $title, Hasil Prediksi = $percent)"
        }
    }

    init {
        model = Interpreter(getModelByteBuffer(assetManager, MODEL_PATH))
        labels = getLabels(assetManager, LABELS_PATH)
    }

    init {
        interpreter = Interpreter(loadModelFile(assetManager, modelPath))
        labellist = loadLabelList(assetManager, labelPath)
    }

    fun recognize(data: ByteArray): List<Detection> {
        val result = Array(BATCH_SIZE) { FloatArray(labels.size) }

        val unscaledBitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
        val bitmap =
                Bitmap.createScaledBitmap(unscaledBitmap, MODEL_INPUT_SIZE, MODEL_INPUT_SIZE2, false)

        val byteBuffer = ByteBuffer
                .allocateDirect(
                        BATCH_SIZE *
                                MODEL_INPUT_SIZE *
                                MODEL_INPUT_SIZE2 *
                                BYTES_PER_CHANNEL *
                                PIXEL_SIZE
                )
                .apply { order(ByteOrder.nativeOrder()) }

        val pixelValues = IntArray(MODEL_INPUT_SIZE * MODEL_INPUT_SIZE2)
        bitmap.getPixels(pixelValues, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)

        var pixel = 0
        for (i in 0 until MODEL_INPUT_SIZE) {
            for (j in 0 until MODEL_INPUT_SIZE) {
                val pixelValue = pixelValues[pixel++]
                byteBuffer.putFloat((pixelValue shr 16 and 0xFF) / 255f)
                byteBuffer.putFloat((pixelValue shr 8 and 0xFF) / 255f)
                byteBuffer.putFloat((pixelValue and 0xFF) / 255f)
            }
        }

        model.run(byteBuffer, result)
        return parseResults(result)
    }

    private fun parseResults(result: Array<FloatArray>): List<Detection> {

        val recognitions = mutableListOf<Detection>()

        labels.forEachIndexed { index, label ->
            val probability = result[0][index]
            recognitions.add(Detection(label, probability))
        }

        return recognitions.sortedByDescending { it.probability }
    }
    
    private fun getModelByteBuffer(assetManager: AssetManager, modelPath: String): ByteBuffer {
        val fileDescriptor = assetManager.openFd(modelPath)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
                .asReadOnlyBuffer()
    }
    
    private fun getLabels(assetManager: AssetManager, labelPath: String): List<String> {
        val labels = ArrayList<String>()
        val reader = BufferedReader(InputStreamReader(assetManager.open(labelPath)))
        while (true) {
            val label = reader.readLine() ?: break
            labels.add(label)
        }
        reader.close()
        return labels
    }

    companion object {
        private const val BATCH_SIZE = 1
        private const val MODEL_INPUT_SIZE = 175
        private const val MODEL_INPUT_SIZE2 = 225
        private const val BYTES_PER_CHANNEL = 4
        private const val PIXEL_SIZE = 3

        private const val LABELS_PATH = "labels.txt"
        private const val MODEL_PATH = "wayang-mobilenet-v2.tflite"
    }

    private fun loadModelFile(assetManager: AssetManager, modelPath: String): MappedByteBuffer {
        val fileDescriptor = assetManager.openFd(modelPath)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    private fun loadLabelList(assetManager: AssetManager, labelPath: String): List<String> {
        return assetManager.open(labelPath).bufferedReader().useLines { it.toList() }

    }

    fun recognizeImage(bitmap: Bitmap): List<Recognition> {
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, inputsize, inputsize2, false)
        val byteBuffer = convertBitmapToByteBuffer(scaledBitmap)
        val result = Array(1) { FloatArray(labels.size) }
        model.run(byteBuffer, result)
        return getSortedResult(result)
    }

    private fun convertBitmapToByteBuffer(bitmap: Bitmap): ByteBuffer {
        val byteBuffer = ByteBuffer.allocateDirect(4 * inputsize * inputsize2 * pixelsize)
        byteBuffer.order(ByteOrder.nativeOrder())
        val intValues = IntArray(inputsize * inputsize2)

        bitmap.getPixels(intValues, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
        var pixel = 0
        for (i in 0 until inputsize) {
            for (j in 0 until inputsize) {
                val `val` = intValues[pixel++]

                byteBuffer.putFloat((((`val`.shr(16)  and 0xFF) - imagemean) / imagestd))
                byteBuffer.putFloat((((`val`.shr(8) and 0xFF) - imagemean) / imagestd))
                byteBuffer.putFloat((((`val` and 0xFF) - imagemean) / imagestd))
            }
        }
        return byteBuffer
    }

    private fun getSortedResult(labelProbArray: Array<FloatArray>): List<Recognition> {
        Log.d("KlasifikasiDariGaleri", "List Size:(%d, %d, %d)".format(labelProbArray.size,labelProbArray[0].size,labels.size))

        val pq = PriorityQueue(
                maxresults,
                Comparator<Recognition> {
                    (_, _, confidence1), (_, _, confidence2)
                    -> confidence1.compareTo(confidence2) * -1
                })

        for (i in labels.indices) {
            val confidence = labelProbArray[0][i]
            if (confidence >= threshold) {
                pq.add(
                        Recognition("" + i,
                                if (labels.size > i) labels[i] else "Unknown", confidence)
                )
            }
        }
        Log.d("KlasifikasiDariGaleri", "pqsize:(%d)".format(pq.size))

        val recognitions = ArrayList<Recognition>()
        val recognitionsSize = pq.size.coerceAtMost(maxresults)
        for (i in 0 until recognitionsSize) {
            recognitions.add(pq.poll())
        }
        return recognitions
    }
}
