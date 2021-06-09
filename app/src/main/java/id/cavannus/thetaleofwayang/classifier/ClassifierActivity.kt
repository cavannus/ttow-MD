package id.cavannus.thetaleofwayang.classifier

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import id.cavannus.thetaleofwayang.R
import id.cavannus.thetaleofwayang.databinding.ActivityClassifierBinding
import java.io.IOException
import java.util.concurrent.Executors

class ClassifierActivity : AppCompatActivity() {
    private lateinit var binding: ActivityClassifierBinding
    private lateinit var classifier: ImageClassifier
    private lateinit var resultbar: TextView
    private lateinit var mBitmap: Bitmap
    private val mInputSize = 175
    private val mInputSize2 = 225
    private val mGalleryRequestCode = 2
    private val mModelPath = "wayang-mobilenet-v5.tflite"
    private val mLabelPath = "labels.txt"

    @SuppressLint("MissingPermission", "SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityClassifierBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getCameraBtnBack.setOnClickListener {
            onBackPressed()
        }

        resultbar = findViewById(R.id.result_bar)
        classifier = ImageClassifier(assets, mModelPath, mLabelPath, mInputSize, mInputSize2)

        if (!canUseCamera()) {
            requestCameraPermissions()
        } else {
            setupCamera()
        }

        binding.btnGetGallery.setOnClickListener {
            val callGalleryIntent = Intent(Intent.ACTION_PICK)
            callGalleryIntent.type = "image/*"
            startActivityForResult(callGalleryIntent, mGalleryRequestCode)
        }

        binding.btnGallery.setOnClickListener {
            val callGalleryIntent = Intent(Intent.ACTION_PICK)
            callGalleryIntent.type = "image/*"
            startActivityForResult(callGalleryIntent, mGalleryRequestCode)
        }

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        binding.btnClassifyGallery.setOnClickListener {
            val results = classifier.recognizeImage(mBitmap).firstOrNull()
            binding.cvResult.visibility = View.VISIBLE
            //binding.galleryResult.text= results?.title+"("+results?.percent+"%)"
            binding.galleryResult.text= results?.title

            binding.btnDetailWayang.setOnClickListener {
                Log.d("CLASSIFIER ACTIVITY", "clicked")
                val uri = Uri.parse("thetaleofwayang://detail")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.putExtra("wayang_result", results?.title)
                startActivity(intent)
           }
        }
    }

    private fun requestCameraPermissions() {
        ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_CAMERA_CODE
        )
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

    @SuppressLint("MissingPermission", "SetTextI18n")
    private fun setupCamera() {
        binding.camera.addPictureTakenListener {
            val executor = Executors.newSingleThreadExecutor()
            executor.execute {
                val recognitions = classifier.recognize(it.data)
                val txt = recognitions[0]
                runOnUiThread {
                    binding.cvResultCamera.visibility = View.VISIBLE
                    resultbar.text = txt.toString()
                    binding.btnDetail.setOnClickListener {
                        Log.d("CLASSIFIER ACTIVITY", "clicked")
                        val uri = Uri.parse("thetaleofwayang://detail")
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        intent.putExtra("wayang_result", txt.toString())
                        startActivity(intent)
                    }
                }
            }
        }

        binding.btnClassify.setOnClickListener {
            binding.camera.capture()
        }

        binding.btnCamera.setOnClickListener {
            binding.getCamera.visibility = View.VISIBLE
            binding.getGallery.visibility = View.GONE
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
        if(requestCode == mGalleryRequestCode && resultCode == RESULT_OK) {
            if (data != null) {
                val uri = data.data

                try {
                    mBitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
                } catch (e: IOException) {
                    e.printStackTrace()
                }

                println("Selesai!")
                mBitmap = scaleImage(mBitmap)
                binding.getCamera.visibility = View.GONE
                binding.getGallery.visibility = View.VISIBLE
                binding.imgResultGallery.setImageBitmap(mBitmap)

            }
        } else {
            Toast.makeText(this, "Pilih gambar wayang dari galeri", Toast.LENGTH_LONG).show()
        }
    }

    private fun scaleImage(bitmap: Bitmap): Bitmap {
        val originalWidth = bitmap.width
        val originalHeight = bitmap.height
        val scaleWidth = mInputSize.toFloat() / originalWidth
        val scaleHeight = mInputSize2.toFloat() / originalHeight
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        return Bitmap.createBitmap(bitmap, 0, 0, originalWidth, originalHeight, matrix, true)
    }
}
