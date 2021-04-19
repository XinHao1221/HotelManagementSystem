package com.example.myapp.fragment.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelmanagementsystem.R
import com.example.myapp.database.Staff
import kotlinx.android.synthetic.main.staff_list.view.*

class StaffListAdapter: RecyclerView.Adapter<StaffListAdapter.MyViewHolder>() {
    private var staffList = emptyList<Staff>()
    class MyViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.staff_list,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = staffList[position]
        holder.itemView.StaffName.text = currentItem.name.toString()
        holder.itemView.StaffID.text = "Staff ID: " + currentItem.staffID.toString()
        holder.itemView.buttonNext.setOnClickListener{
            val bundle = bundleOf("staffID" to currentItem.staffID)
            Toast.makeText(holder.itemView.context, currentItem.staffID, Toast.LENGTH_SHORT).show()
            // Using the Kotlin extension in the -ktx artifacts
            // Alternatively, use Navigation.findNavController(holder.itemView)
            holder.itemView.findNavController().navigate(
                R.id.action_staffManagement_to_setStaffProfile, bundle)
        }
    }


    override fun getItemCount(): Int {
        return staffList.size
    }
    fun setData(staff: List<Staff>){
        this.staffList = staff
        notifyDataSetChanged()
    }
}