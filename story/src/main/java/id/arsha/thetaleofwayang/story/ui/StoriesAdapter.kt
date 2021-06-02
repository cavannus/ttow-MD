package id.arsha.thetaleofwayang.story.ui

//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import id.arsha.thetaleofwayang.story.R
//import id.arsha.thetaleofwayang.story.databinding.ListWayangBinding
//import id.arsha.thetaleofwayang.story.domain.model.Stories
//import java.util.*
//
//class StoriesAdapter : RecyclerView.Adapter<StoriesAdapter.ListViewHolder>() {
//
//    internal var listData = ArrayList<Stories>()
//    var onItemClick: ((Stories) -> Unit)? = null
//
//    fun setData(newListData: List<Stories>?) {
//        if (newListData == null) return
//        listData.clear()
//        listData.addAll(newListData)
//        notifyDataSetChanged()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
//        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_wayang, parent, false))
//
//    override fun getItemCount() = listData.size
//
//    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//        val data = listData[position]
//        holder.bind(data)
//    }
//
//    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val binding = ListWayangBinding.bind(itemView)
//        fun bind(data: Stories) {
//            with(binding) {
////                Glide.with(itemView.context)
////                    .load(data.image_url)
////                    .into(ivAvatar)
//                tvUsername.text = data.nm_wayang
//                tvName.text = data.desc_wayang
//            }
//        }
//
//        init {
//            binding.root.setOnClickListener {
//                onItemClick?.invoke(listData[adapterPosition])
//            }
//        }
//    }
//}