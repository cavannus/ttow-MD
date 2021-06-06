package id.cavannus.thetaleofwayang

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import id.cavannus.thetaleofwayang.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    //private lateinit var currentPhotoPath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.elevation = 0f

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
                setOf(
                        R.id.nav_home,
                        R.id.nav_gallery,
                        R.id.nav_search,
                        R.id.nav_favorite
                ), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        val fab: FloatingActionButton = findViewById(R.id.fab)
//        fab.setOnClickListener {
//            dispatchTakePictureIntent()
//        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        //TESTING RESULT OK
//        //if result ok, image will appear on Home Fragment
//        if(requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK){
//            val imgView: ImageView = findViewById(R.id.img_result)
//            if(currentPhotoPath.isNotEmpty()){
//                Glide
//                        .with(this)
//                        .load(currentPhotoPath)
//                        .apply(RequestOptions().override(200))
//                        .into(imgView)
//            }
//        }
//    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

//    private fun dispatchTakePictureIntent() {
//        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
//            takePictureIntent.resolveActivity(packageManager)?.also {
//                val photoFile: File? = try {
//                    createImageFile()
//                } catch (ex: IOException) {
//                    null
//                }
//
//                photoFile?.also {
//                    val photoURI: Uri = FileProvider.getUriForFile(
//                            this,
//                            "id.cavannus.thetaleofwayang",
//                            it
//                    )
//                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
//                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
//                }
//            }
//        }
//    }
//
//    @SuppressLint("SimpleDateFormat")
//    private fun createImageFile(): File {
//        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
//        //image will restore in Android/data/id.cavannus.thetaleofwayang/file/Pictures
//        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
//        return File.createTempFile(
//                "JPEG_${timeStamp}_",
//                ".jpg",
//                storageDir
//        ).apply {
//            currentPhotoPath = absolutePath
//        }
//    }
//
//    companion object {
//        private const val REQUEST_TAKE_PHOTO = 1
//    }
}