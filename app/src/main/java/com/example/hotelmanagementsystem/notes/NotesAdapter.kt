package com.example.hotelmanagementsystem.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelmanagementsystem.R
import com.example.hotelmanagementsystem.database.NotesDB.NotesEntity
import kotlinx.android.synthetic.main.fragment_notes_details.view.*
import kotlinx.android.synthetic.main.notes_list.view.*

class NotesAdapter(): RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private var data = emptyList<NotesEntity>()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.notes_list, parent, false))


    }

    // Return the no of data inside Reservation Table
    override fun getItemCount(): Int{
        return data.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        // Format reservation id before display
        var notesID = formatNotesID(item)

        holder.itemView.notes_list_id.text = notesID
        holder.itemView.notes_list_title.text = item.notesTitle

        // Set onclick listener to individual item on recycle view
        holder.itemView.notes_cardView.setOnClickListener{
            // Pass Reservation object to the update fragment
            val action = NotesFragmentDirections.actionNotesFragmentToNotesDetailsFragment(item)
            holder.itemView.findNavController().navigate(action)
        }

    }

    fun formatNotesID(item : NotesEntity):String{

        var notesID = item.notesId.toString()

        var id:Int = 1000 + notesID.toInt()

        notesID = "N" + id.toString()

        return notesID.toString()
    }


    fun setData(notes: List<NotesEntity>){

        this.data = notes
        notifyDataSetChanged()
    }

}