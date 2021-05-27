package id.cavannus.thetaleofwayang.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.cavannus.thetaleofwayang.core.R
import id.cavannus.thetaleofwayang.core.databinding.ItemListWayangBinding
import id.cavannus.thetaleofwayang.core.domain.model.Wayang
import java.util.*

class WayangAdapter : RecyclerView.Adapter<WayangAdapter.ListViewHolder>() {

    internal var listData = ArrayList<Wayang>()
    var onItemClick: ((Wayang) -> Unit)? = null

    fun setData(newListData: List<Wayang>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_wayang, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListWayangBinding.bind(itemView)
        fun bind(data: Wayang) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.image_url)
                    .into(ivItemImage)
                tvItemTitle.text = data.nama
                tvItemSubtitle.text = data.golongan
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}