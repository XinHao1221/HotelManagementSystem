package com.example.hotelmanagementsystem.checkout.recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelmanagementsystem.R
import com.example.hotelmanagementsystem.checkout.CheckOutMenuFragmentDirections
import com.example.hotelmanagementsystem.database.reservations.Reservation
import kotlinx.android.synthetic.main.res_list.view.*

class CheckOutAdapter:RecyclerView.Adapter<CheckOutAdapter.ViewHolder>() {

    private var data = emptyList<Reservation>()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.res_list, parent, false))

    }

    // Return the no of data inside Reservation Table
    override fun getItemCount(): Int{
        return data.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        // Format reservation id before display
        var reservationID = formatReservationID(item)

        holder.itemView.id_txt.text = reservationID
        holder.itemView.name.text = item.guestName
        holder.itemView.room_type_txt.text = item.roomType.toString()

        // Format the duration of stay before displaying
        var durationOfStay = item.checkInDate + " - " + item.checkOutDate
        holder.itemView.duration_stay_txt.text = durationOfStay.toString()

        // Set onclick listener to individual item on recycle view
        holder.itemView.reservation_menu_cardView.setOnClickListener{
            // Pass Reservation object to the update fragment

            val action = CheckOutMenuFragmentDirections.actionCheckOutMenuFragmentToCheckOutDetailFragment(item)
            holder.itemView.findNavController().navigate(action)
        }


        //bind(holder, item)
    }

    fun formatReservationID(item : Reservation):String{

        var reservationID = item.reservationId.toString()

        var id:Int = 100000 + reservationID.toInt()

        reservationID = "R" + id.toString()

        return reservationID.toString()
    }
//    private fun bind(holder: ViewHolder, item: Reservation) {
//        holder.bind(item)
//

//    }

    fun setData(reservation: List<Reservation>){

        this.data = reservation
        notifyDataSetChanged()
    }

//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
//
////        val reservationId:TextView = itemView.findViewById(R.id.id_txt)
////        val name:TextView = itemView.findViewById(R.id.name)
////        val roomType:TextView = itemView.findViewById(R.id.room_type)
////        val durationOfStay:TextView = itemView.findViewById(R.id.duration_stay_txt)
////
////        fun bind(item: Reservation){
////            val res = itemView.context.resources
////
////            reservationId.text = item.reservationId.toString()
////            name.text = item.guestName
////            roomType.text = item.room_type
////            durationOfStay.text = item.check_in_date.toString()
////
////        }
////
////        companion object {
////            fun from(parent: ViewGroup): ViewHolder {
////                val layoutInflater = LayoutInflater.from(parent.context)
////                val view = layoutInflater
////                    .inflate(R.layout.fragment_reservation_item_list, parent, false)
////
////                return ViewHolder(view)
////            }
////        }
//
//    }
}