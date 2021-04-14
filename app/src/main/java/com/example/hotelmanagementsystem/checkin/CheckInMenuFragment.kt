package com.example.hotelmanagementsystem.checkin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotelmanagementsystem.R
import com.example.hotelmanagementsystem.checkin.recycleview.CheckInAdapter
import com.example.hotelmanagementsystem.hotelreservation.ReservationAdapter
import com.example.hotelmanagementsystem.hotelreservation.viewmodel.ReservationDatabaseViewModel
import com.example.hotelmanagementsystem.hotelreservation.viewmodel.ReservationViewModel
import kotlinx.android.synthetic.main.fragment_check_in_menu.view.*
import kotlinx.android.synthetic.main.fragment_reservation_menu.view.*


class CheckInMenuFragment : Fragment() {

    private lateinit var reservationDatabaseViewModel: ReservationDatabaseViewModel
    private val sharedViewModel: ReservationViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_check_in_menu, container, false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Check In"

        reservationDatabaseViewModel = ViewModelProvider(this).get(ReservationDatabaseViewModel::class.java)

        // Reset the sharedViewModel to empty
        clearSharedViewModel()

        // Recycle view
        val adapter = CheckInAdapter()
        val recyclerView = view.check_in_recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // ReservationViewModel
        reservationDatabaseViewModel = ViewModelProvider(this).get(ReservationDatabaseViewModel::class.java)
        reservationDatabaseViewModel.todaysCheckIn.observe(viewLifecycleOwner, Observer { reservation ->
            adapter.setData(reservation)
        })



        return view
    }

    fun clearSharedViewModel(){
        sharedViewModel.setGuestName("")
        sharedViewModel.setIdType("")
        sharedViewModel.setIDNo("")
        sharedViewModel.setRoomType("")
        sharedViewModel.setCheckInDate("")
        sharedViewModel.setCheckOutDate("")
        sharedViewModel.setEmail("")
        sharedViewModel.setPhoneNo("")
        sharedViewModel.setTotalAmount("")
        sharedViewModel.setPaymentMethod("")
        sharedViewModel.setReferenceNo("")
    }


}