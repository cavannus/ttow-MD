package id.cavannus.thetaleofwayang.classifier

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Bundle
import android.os.SystemClock
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import id.cavannus.thetaleofwayang.databinding.ActivityGetGalleryBinding
import java.io.IOException

class GetGalleryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGetGalleryBinding
    private lateinit var mBitmap: Bitmap
    private lateinit var mClassifier: ClassifyFromGallery

    private val mInputSize = 175
    private val mInputSize2 = 225
    private val mGalleryRequestCode = 2
    private val mModelPath = "wayang-mobilenet-v2.tflite"
    private val mLabelPath = "labels.txt"
    private val mSamplePath = "semar.jpg"
    private var lastProcessingTimeMs: Long = 0

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGetGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetGallery.setOnClickListener {
            val callGalleryIntent = Intent(Intent.ACTION_PICK)
            callGalleryIntent.type = "image/*"
            startActivityForResult(callGalleryIntent, mGalleryRequestCode)
        }

        binding.getGalleryBtnBack.setOnClickListener{
            onBackPressed()
        }

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        mClassifier = ClassifyFromGallery(assets, mModelPath, mLabelPath, mInputSize, mInputSize2)

        resources.assets.open(mSamplePath).use {
            mBitmap = BitmapFactory.decodeStream(it)
            mBitmap = Bitmap.createScaledBitmap(mBitmap, mInputSize, mInputSize2, true)
            binding.imgResultGallery.setImageBitmap(mBitmap)
        }

        binding.btnClassify.setOnClickListener {
            val startTime = SystemClock.uptimeMillis()//menghitung waktu awal
            val results = mClassifier.recognizeImage(mBitmap).firstOrNull()
            binding.mResultTextView.text= results?.title+"\n Probabilitas: "+results?.percent+"%"
            lastProcessingTimeMs = SystemClock.uptimeMillis() - startTime//menghitung lamanya proses
            //val waktu = lastProcessingTimeMs.toString()//konversi ke string
            //binding.delaytime.text = "$waktu ms "

        }
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