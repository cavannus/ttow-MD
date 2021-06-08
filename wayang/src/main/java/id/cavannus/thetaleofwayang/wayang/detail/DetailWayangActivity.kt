package id.cavannus.thetaleofwayang.wayang.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import id.cavannus.thetaleofwayang.R
import id.cavannus.thetaleofwayang.core.R.drawable.img_placeholder
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
        private var detailWayang: Wayang? = null
    }

    private val detailWayangViewModel: DetailWayangViewModel by viewModel()
    private lateinit var binding: ActivityDetailWayangBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadKoinModules(wayangModule)

        binding = ActivityDetailWayangBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        detailWayang = intent.getParcelableExtra(EXTRA_DATA)
        val namaWayang = detailWayang?.nm_wayang

        val storiesAdapter = StoriesAdapter()
        storiesAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailStoriesActivity::class.java)
            intent.putExtra(DetailStoriesActivity.EXTRA_STORIES, selectedData)
            startActivity(intent)
        }

        if (namaWayang != null) {
            detailWayangViewModel.getFavorite(namaWayang).observe(this) { wayang ->
                if(wayang != null) {
                    checkStatusFavorite(true)
                    binding.fab.setOnClickListener{
                        detailWayang?.let { it1 -> setStatusFavorite(true, it1) }
                    }
                }else{
                    checkStatusFavorite(false)
                    binding.fab.setOnClickListener{
                        detailWayang?.let { it1 -> setStatusFavorite(false, it1) }
                    }
                }
            }

            detailWayangViewModel.getAllStories(namaWayang).observe(this) { wayang ->
                if (wayang != null) {
                    when (wayang) {
                        is Resource.Loading<*> -> binding.detailProgressBar.visibility = View.VISIBLE
                        is Resource.Success<*> -> {
                            binding.detailProgressBar.visibility = View.GONE
                            showDetailWayang(detailWayang)
                            storiesAdapter.setData(wayang.data)
                        }
                        is Resource.Error<*> -> {
                            binding.detailProgressBar.visibility = View.GONE
                        }
                    }
                }
            }
        }else{
            val wayangResult = Uri.parse(intent.extras?.getString("wayang_result")).toString()

            val regex = """[a-zA-Z]*""".toRegex()
            val wayangResultFiltered = regex.find(wayangResult)?.value

            if(wayangResultFiltered?.isNotEmpty() == true){
                detailWayangViewModel.getWayangByName(wayangResultFiltered)
                        .observe(this){ wayang ->
                                if (wayang != null){
                                    when(wayang) {
                                        is Resource.Loading -> binding.detailProgressBar.visibility = View.VISIBLE
                                        is Resource.Success -> {
                                            showDetailWayang(wayang.data)
                                        }
                                        is Resource.Error -> {
                                            binding.detailProgressBar.visibility = View.GONE
                                    }
                                }
                            }
                        }

                detailWayangViewModel.getFavorite(wayangResultFiltered).observe(this) { wayang ->
                    if(wayang != null) {
                        checkStatusFavorite(true)
                        binding.fab.setOnClickListener{
                            detailWayang?.let { it1 -> setStatusFavorite(true, it1) }
                        }
                    }else{
                        checkStatusFavorite(false)
                        binding.fab.setOnClickListener{
                            detailWayang?.let {
                                it1 -> setStatusFavorite(false, it1)
                            }
                        }
                    }
                }

                detailWayangViewModel.getAllStories(wayangResultFiltered).observe(this) { wayang ->
                    if (wayang != null) {
                        when (wayang) {
                            is Resource.Loading<*> -> binding.detailProgressBar.visibility = View.VISIBLE
                            is Resource.Success<*> -> {
                                binding.detailProgressBar.visibility = View.GONE
                                storiesAdapter.setData(wayang.data)
                            }
                            is Resource.Error<*> -> {
                                binding.detailProgressBar.visibility = View.GONE
                            }
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
            binding.detailToolbarTitle.text = detailWayang.nm_wayang
            binding.wayangName.text = detailWayang.nm_wayang
            binding.tvWatak.text = detailWayang.watak_wayang
            Glide.with(this@DetailWayangActivity)
                .load(detailWayang.foto_wayang2)
                .placeholder(img_placeholder)
                .override(500)
                .into(binding.imgItem)
        }
    }

    private fun checkStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean, detailWayang: Wayang) {
        if (statusFavorite) {
            checkStatusFavorite(statusFavorite)
            detailWayangViewModel.delFavoriteWayang(detailWayang)
            showSnackbar(resources.getString(R.string.del_fav, detailWayang.nm_wayang))
        } else {
            checkStatusFavorite(statusFavorite)
            detailWayangViewModel.addFavoriteWayang(detailWayang)
            showSnackbar(resources.getString(R.string.add_fav, detailWayang.nm_wayang))
        }
    }

    private fun showSnackbar(msg: String) {
        Snackbar.make(binding.parentLayout, msg, Snackbar.LENGTH_SHORT).show()
    }
}
