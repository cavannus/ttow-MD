package id.cavannus.thetaleofwayang.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.cavannus.thetaleofwayang.core.R
import id.cavannus.thetaleofwayang.core.databinding.ListStoriesBinding
import id.cavannus.thetaleofwayang.core.domain.model.Stories
import id.cavannus.thetaleofwayang.core.utils.Helper
import java.util.*

class StoriesAdapter : RecyclerView.Adapter<StoriesAdapter.ListViewHolder>() {

    internal var listData = ArrayList<Stories>()
    var onItemClick: ((Stories) -> Unit)? = null

    fun setData(newListData: List<Stories>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_stories, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListStoriesBinding.bind(itemView)
        fun bind(data: Stories) {
            with(binding) {
                tvStoryTitle.text = data.judul
                tvStorySubtitle.text = data.cerita
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}