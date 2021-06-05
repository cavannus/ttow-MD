package id.cavannus.thetaleofwayang.wayang.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import id.cavannus.thetaleofwayang.R
import id.cavannus.thetaleofwayang.core.data.Resource
import id.cavannus.thetaleofwayang.core.domain.model.Wayang
import id.cavannus.thetaleofwayang.core.ui.StoriesAdapter
import id.cavannus.thetaleofwayang.stories.DetailStoriesActivity
import id.cavannus.thetaleofwayang.wayang.databinding.ActivityDetailWayangBinding
import id.cavannus.thetaleofwayang.wayang.di.wayangModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class DetailWayangActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailWayangViewModel: DetailWayangViewModel by viewModel()
    private lateinit var binding: ActivityDetailWayangBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadKoinModules(wayangModule)

        binding = ActivityDetailWayangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailWayang = intent.getParcelableExtra<Wayang>(EXTRA_DATA)
        val namaWayang = detailWayang?.nm_wayang
        namaWayang?.let { Log.d("DETAIL WAYANG", it) }
        showDetailWayang(detailWayang)

        val storiesAdapter = StoriesAdapter()
        storiesAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailStoriesActivity::class.java)
            intent.putExtra(DetailStoriesActivity.EXTRA_STORIES, selectedData)
            startActivity(intent)
        }

        if (namaWayang != null) {
            detailWayangViewModel.getAllStories(namaWayang).observe(this) { wayang ->
                if (wayang != null) {
                    Log.d("DETAIL WAYANG OBSERVE", wayang.data.toString())
                    when (wayang) {
                        is Resource.Loading<*> -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success<*> -> {
                            binding.progressBar.visibility = View.GONE
                            storiesAdapter.setData(wayang.data)
                        }
                        is Resource.Error<*> -> {
                            binding.progressBar.visibility = View.GONE
//                        binding.lottieError.visibility = View.VISIBLE
//                        binding.textError.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }

        with(binding.rvStories) {
            this.layoutManager = LinearLayoutManager(context)
            this.setHasFixedSize(true)
            this.adapter = storiesAdapter
        }

        binding.detailBtnBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun showDetailWayang(detailWayang: Wayang?) {
        detailWayang?.let {
            //supportActionBar?.title = detailWayang.nm_wayang
            binding.detailToolbarTitle.text = detailWayang.nm_wayang
            binding.wayangName.text = detailWayang.nm_wayang
            binding.tvWatak.text = detailWayang.watak_wayang
            Glide.with(this@DetailWayangActivity)
                .load(detailWayang.foto_wayang)
                .override(500)
                .into(binding.imgItem)

//            binding.detailContent.btnVideo.setOnClickListener {
//                val video = detailWayang.strYoutube
//                val i = Intent(Intent.ACTION_VIEW, Uri.parse(video))
//                startActivity(i)
//            }

            var statusFavorite = detailWayang.isFavorite
            checkStatusFavorite(detailWayang.isFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailWayangViewModel.setFavoriteWayang(detailWayang, statusFavorite)
                setStatusFavorite(statusFavorite, detailWayang)
            }
        }
    }

    private fun checkStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean, detailWayang: Wayang?) {
        if (statusFavorite) {
            checkStatusFavorite(statusFavorite)
            showSnackbar(resources.getString(R.string.add_fav, detailWayang?.nm_wayang))
        } else {
            checkStatusFavorite(statusFavorite)
            showSnackbar(resources.getString(R.string.rem_fav, detailWayang?.nm_wayang))
        }
    }

    private fun showSnackbar(msg: String) {
        Snackbar.make(binding.parentLayout, msg, Snackbar.LENGTH_SHORT).show()
    }
}
