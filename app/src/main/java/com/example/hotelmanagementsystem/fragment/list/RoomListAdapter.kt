package com.example.hotelmanagementsystem.fragment.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelmanagementsystem.R
import com.example.hotelmanagementsystem.database.rooms.HotelRoom
import kotlinx.android.synthetic.main.room_list.view.*

class RoomListAdapter: RecyclerView.Adapter<RoomListAdapter.MyViewHolder>() {
    private var roomList = emptyList<HotelRoom>()
    class MyViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.room_list,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = roomList[position]
        holder.itemView.textRoomName.text = currentItem.roomName.toString()
        holder.itemView.textRoomType.text = "Type: " + currentItem.roomType.toString()
        holder.itemView.priceOfWorkday.text = "Workday: RM " + String.format("%.2f",currentItem.priceWorkday)
        holder.itemView.priceOfWeekend.text = "Weekend: RM " + String.format("%.2f",currentItem.priceWeekend)
        holder.itemView.priceOfHoliday.text = "Holiday: RM " + String.format("%.2f",currentItem.priceHoliday)
        holder.itemView.buttonNext.setOnClickListener{
            val bundle = bundleOf("RoomID" to currentItem.roomID)
            Toast.makeText(holder.itemView.context, currentItem.roomID, Toast.LENGTH_SHORT).show()
            // Using the Kotlin extension in the -ktx artifacts
            // Alternatively, use Navigation.findNavController(holder.itemView)
            holder.itemView.findNavController().navigate(
                    R.id.action_roomManagement_to_setRoomDetails, bundle)
        }
    }

    override fun getItemCount(): Int {
        return roomList.size
    }
    fun setData(room:List<HotelRoom>){
        this.roomList = room
        notifyDataSetChanged()
    }
}