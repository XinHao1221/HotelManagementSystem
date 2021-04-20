package com.example.hotelmanagementsystem.fragment.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelmanagementsystem.R
import com.example.hotelmanagementsystem.database.floors.FloorWithRoom
import com.example.hotelmanagementsystem.database.rooms.HotelRoom
import kotlinx.android.synthetic.main.floor_list.view.*


class FloorListAdapter: RecyclerView.Adapter<FloorListAdapter.MyViewHolder>() {
    private var floorList = emptyList<FloorWithRoom>()
    class MyViewHolder (itemView: View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.floor_list,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = floorList[position]
        holder.itemView.textFloor.text = currentItem.floor.floorName.toString()
        val selectFloor:String = currentItem.floor.floorID.toString()
        val rList: List<HotelRoom> = currentItem.room
        val roomName:MutableList<String> = mutableListOf()
        for (i in rList.indices) {
            if (rList.get(i).floorID.toString().equals(selectFloor)) {
                roomName.add(rList.get(i).roomID.toString().substring(1))
            }
        }
        holder.itemView.textRoom.text = roomName.toString()

        holder.itemView.buttonNext.setOnClickListener{
            val bundle = bundleOf("floorID" to currentItem.floor.floorID)
            Toast.makeText(holder.itemView.context, currentItem.floor.floorID, Toast.LENGTH_SHORT).show()
            // Using the Kotlin extension in the -ktx artifacts
            // Alternatively, use Navigation.findNavController(holder.itemView)
            holder.itemView.findNavController().navigate(
                    R.id.action_floorManagement_to_roomManagement, bundle)
        }
    }


    override fun getItemCount(): Int {
        return floorList.size
    }
    fun setData(floor:List<FloorWithRoom>){
        this.floorList = floor
        notifyDataSetChanged()
    }

}

