package id.cavannus.thetaleofwayang.stories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import id.cavannus.thetaleofwayang.core.domain.model.Stories
import id.cavannus.thetaleofwayang.stories.databinding.ActivityDetailStoriesBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailStoriesActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_STORIES = "extra_stories"
    }

    private lateinit var binding: ActivityDetailStoriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailStoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.storyBtnBack.setOnClickListener{
            onBackPressed()
        }

        val detailStories = intent.getParcelableExtra<Stories>(EXTRA_STORIES)
        showDetailStories(detailStories)
    }

    private fun showDetailStories(detailStories: Stories?) {
        detailStories?.let {
            supportActionBar?.title = detailStories.judul
            binding.storyTitle.text = detailStories.judul

            binding.storyContent.text = detailStories.cerita

            Glide.with(this@DetailStoriesActivity)
                .load(R.drawable.punakawan)
                .into(binding.storyImage)
        }
    }
}