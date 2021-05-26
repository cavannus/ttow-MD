package id.cavannus.thetaleofwayang.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import id.cavannus.thetaleofwayang.MainActivity
import id.cavannus.thetaleofwayang.R
import id.cavannus.thetaleofwayang.core.domain.model.Wayang
import id.cavannus.thetaleofwayang.databinding.ActivityDetailWayangBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailWayangActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailWayangViewModel: DetailWayangViewModel by viewModel()
    private lateinit var binding: ActivityDetailWayangBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailWayangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        val detailWayang = intent.getParcelableExtra<Wayang>(EXTRA_DATA)
        showDetailWayang(detailWayang)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun showDetailWayang(detailWayang: Wayang?) {
        detailWayang?.let {
            supportActionBar?.title = detailWayang.nama
            binding.detailContent.wayangName.text = detailWayang.nama
            binding.detailContent.textArea.text = detailWayang.golongan
            binding.detailContent.textInstruction.text = detailWayang.ayah
            Glide.with(this@DetailWayangActivity)
                .load(detailWayang.image_url)
                .into(binding.detailContent.imgItem)
//            binding.detailContent.btnVideo.setOnClickListener {
//                val video = detailWayang.strYoutube
//                val i = Intent(Intent.ACTION_VIEW, Uri.parse(video))
//                startActivity(i)
//            }

            var statusFavorite = detailWayang.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailWayangViewModel.setFavoriteWayang(detailWayang, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }
}
