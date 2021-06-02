package id.cavannus.thetaleofwayang.stories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.cavannus.thetaleofwayang.core.domain.model.Stories
import id.cavannus.thetaleofwayang.core.domain.model.Wayang
import id.cavannus.thetaleofwayang.stories.databinding.ActivityDetailStoriesBinding

class DetailStoriesActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_STORIES = "extra_stories"
    }

    private lateinit var binding: ActivityDetailStoriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailStoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailStories = intent.getParcelableExtra<Stories>(EXTRA_STORIES)
        showDetailStories(detailStories)

        binding.storyBtnBack.setOnClickListener{
            onBackPressed()
        }
    }

    private fun showDetailStories(detailStories: Stories?) {
        detailStories?.let {
            supportActionBar?.title = detailStories.nm_wayang
            binding.storyTitle.text = detailStories.nm_wayang

//            Glide.with(this@DetailWayangActivity)
//                .load(detailWayang.image_url)
//                .into(binding.imgItem)
        }
    }
}