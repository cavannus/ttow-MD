package id.cavannus.thetaleofwayang.classifier

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Bundle
import android.os.SystemClock
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import id.cavannus.thetaleofwayang.R
import id.cavannus.thetaleofwayang.databinding.ActivityGetCameraBinding

class GetCameraActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGetCameraBinding

    private lateinit var classifier: ClassifyFromCamera //mendeklarasikan komponen TextView resultbar yang akan dimanipulasi
    private lateinit var resultbar: TextView
    private lateinit var processtime: TextView
    private var lastProcessingTimeMs: Long = 0 //deklarasi variabel lastprocessingtimems bertipe data long

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGetCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getCameraBtnBack.setOnClickListener {
            onBackPressed()
        }

        resultbar = findViewById(R.id.result_bar)
        processtime = findViewById(R.id.process_time_bar)

        classifier = ClassifyFromCamera(assets)

        if (!canUseCamera()) {
            requestCameraPermissions()
        } else {
            setupCamera()
        }
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

        binding.capturePhoto.setOnClickListener {
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
}
