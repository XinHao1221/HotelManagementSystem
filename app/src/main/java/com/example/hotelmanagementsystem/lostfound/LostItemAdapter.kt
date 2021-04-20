package com.example.hotelmanagementsystem.lostfound

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelmanagementsystem.R
import com.example.hotelmanagementsystem.database.lostitems.LostItem
import kotlinx.android.synthetic.main.lost_item_list.view.*

class LostItemAdapter(): RecyclerView.Adapter<LostItemAdapter.ViewHolder>() {

    var data =  emptyList<LostItem>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
        holder.itemView.lost_item_card.setOnClickListener{ view ->
            val action = LostfoundMenuFragmentDirections.actionLostfoundMenuFragmentToEditLostItemFragment(item)
            view.findNavController().navigate(action)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val layout = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.lost_item_list, parent, false)

        return ViewHolder(layout)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val lostItemImg: ImageView = itemView.findViewById(R.id.lost_item_img)
        private val lostLocation: TextView = itemView.findViewById(R.id.lost_loc)
        private val lostDescription: TextView = itemView.findViewById(R.id.lost_desc)
        private val lostStatus: TextView = itemView.findViewById(R.id.lost_status)
        private val foundTime: TextView = itemView.findViewById(R.id.found_time)

        fun bind(item: LostItem) {
            lostItemImg.setImageBitmap(item.itemImg)
            lostLocation.text = item.lostLocation
            lostDescription.text = item.description
            if (item.foundStatus) {
                lostStatus.text = "Found"
                var dateStr = DateFormat.format("dd-MM-yyyy hh:mm:ss a", item.foundTime)
                foundTime.text = dateStr
            } else {
                lostStatus.text = "Not Found"
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater =
                        LayoutInflater.from(parent.context)
                val view = layoutInflater
                        .inflate(
                            R.layout.lost_item_list,
                            parent, false
                        )
                return ViewHolder(view)
            }
        }
    }
}
