package id.cavannus.thetaleofwayang.classifier

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.AsyncTask
import android.os.Bundle
import android.os.SystemClock
import android.provider.MediaStore
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import id.cavannus.thetaleofwayang.R
import id.cavannus.thetaleofwayang.databinding.ActivityClassifierBinding
import id.cavannus.thetaleofwayang.databinding.ActivityGetCameraBinding
import id.cavannus.thetaleofwayang.databinding.ActivityGetGalleryBinding
import java.io.IOException

class ClassifierActivity : AppCompatActivity() {
    private lateinit var binding: ActivityClassifierBinding

    private lateinit var classifier: ImageClassifier //mendeklarasikan komponen TextView resultbar yang akan dimanipulasi
    private lateinit var resultbar: TextView
    private lateinit var processtime: TextView
    private var lastProcessingTimeMs: Long = 0 //deklarasi variabel lastprocessingtimems bertipe data long

    private lateinit var mBitmap: Bitmap
    private val mInputSize = 175
    private val mInputSize2 = 225
    private val mGalleryRequestCode = 2
    private val mModelPath = "wayang-mobilenet-v2.tflite"
    private val mLabelPath = "labels.txt"
    private val mSamplePath = "semar.jpg"

    @SuppressLint("MissingPermission", "SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityClassifierBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getCameraBtnBack.setOnClickListener {
            onBackPressed()
        }

        resultbar = findViewById(R.id.result_bar)
        processtime = findViewById(R.id.process_time_bar)

        classifier = ImageClassifier(assets, mModelPath, mLabelPath, mInputSize, mInputSize2)

        if (!canUseCamera()) {
            requestCameraPermissions()
        } else {
            setupCamera()
        }

        //Get dari gallery
        binding.btnGetGallery.setOnClickListener {
            val callGalleryIntent = Intent(Intent.ACTION_PICK)
            callGalleryIntent.type = "image/*"
            startActivityForResult(callGalleryIntent, mGalleryRequestCode)
        }

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        resources.assets.open(mSamplePath).use {
            mBitmap = BitmapFactory.decodeStream(it)
            mBitmap = Bitmap.createScaledBitmap(mBitmap, mInputSize, mInputSize2, true)
            binding.imgResultGallery.setImageBitmap(mBitmap)
        }

//        binding.btnClassify.setOnClickListener {
//            val startTime = SystemClock.uptimeMillis()//menghitung waktu awal
//            val results = mClassifier.recognizeImage(mBitmap).firstOrNull()
//            binding.mResultTextView.text= results?.title+"\n Probabilitas: "+results?.percent+"%"
//            lastProcessingTimeMs = SystemClock.uptimeMillis() - startTime//menghitung lamanya proses
//            //val waktu = lastProcessingTimeMs.toString()//konversi ke string
//            //binding.delaytime.text = "$waktu ms "
//
//        }
    }

    private fun requestCameraPermissions() {
        ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_CAMERA_CODE
        )
    }

    @SuppressLint("MissingPermission", "SetTextI18n")
    private fun setupCamera() {
        binding.camera.addPictureTakenListener {
            AsyncTask.execute {
                val startTime = SystemClock.uptimeMillis()//menghitung waktu awal
                val recognitions = classifier.recognize(it.data)
                val txt = recognitions.joinToString(separator = "\n")
                lastProcessingTimeMs = SystemClock.uptimeMillis() - startTime//menghitung lamanya proses
                val waktu = lastProcessingTimeMs.toString()//konversi ke string
                runOnUiThread {
                    /*menampilkan hasil pada UI*/
                    //Toast.makeText(this, txt, Toast.LENGTH_LONG).show()
                    /*menampilkan hasil pada layout TextView*/
                    resultbar.text = txt
                    processtime.text = "$waktu ms "
                }
            }
        }

        binding.btnClassify.setOnClickListener {
            binding.camera.capture()
        }

    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (REQUEST_CAMERA_CODE == requestCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupCamera()
            } else {
                Toast.makeText(this, "App needs camera in order to work.", Toast.LENGTH_LONG).show()
                requestCameraPermissions()
            }
        }
    }

    @SuppressLint("MissingPermission")
    override fun onResume() {
        super.onResume()
        if (canUseCamera()) {
            binding.camera.start()
        }
    }

    override fun onPause() {
        if (canUseCamera()) {
            binding.camera.stop()
        }
        super.onPause()
    }

    override fun onDestroy() {
        if (canUseCamera()) {
            binding.camera.destroy()
        }
        super.onDestroy()
    }

    private fun canUseCamera() =
            ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED

    companion object {
        private const val REQUEST_CAMERA_CODE = 1
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == mGalleryRequestCode) {
            if (data != null) {
                val uri = data.data

                try {
                    mBitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
                } catch (e: IOException) {
                    e.printStackTrace()
                }

                println("Selesai!")
                mBitmap = scaleImage(mBitmap)
                binding.imgResultGallery.setImageBitmap(mBitmap)

            }
        } else {
            Toast.makeText(this, "Unrecognized request code", Toast.LENGTH_LONG).show()

        }
    }

    private fun scaleImage(bitmap: Bitmap?): Bitmap {
        val orignalWidth = bitmap!!.width
        val originalHeight = bitmap.height
        val scaleWidth = mInputSize.toFloat() / orignalWidth
        val scaleHeight = mInputSize2.toFloat() / originalHeight
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        return Bitmap.createBitmap(bitmap, 0, 0, orignalWidth, originalHeight, matrix, true)
    }
}