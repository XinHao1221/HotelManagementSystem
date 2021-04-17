package com.example.hotelmanagementsystem.checkin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotelmanagementsystem.R
import com.example.hotelmanagementsystem.checkin.recycleview.CheckInAdapter
import com.example.hotelmanagementsystem.databinding.FragmentCheckInMenuBinding
import com.example.hotelmanagementsystem.databinding.FragmentGuestsInHouseBinding
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
        val binding = DataBindingUtil.inflate<FragmentCheckInMenuBinding>(inflater,
                R.layout.fragment_check_in_menu,container,false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Check In"

        reservationDatabaseViewModel = ViewModelProvider(this).get(ReservationDatabaseViewModel::class.java)

        // Reset the sharedViewModel to empty
        clearSharedViewModel()

        // Recycle view
        val adapter = CheckInAdapter()
        val recyclerView = binding.checkInRecyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // ReservationViewModel
        reservationDatabaseViewModel = ViewModelProvider(this).get(ReservationDatabaseViewModel::class.java)
        reservationDatabaseViewModel.todaysCheckIn.observe(viewLifecycleOwner, Observer { reservation ->
            adapter.setData(reservation)
        })

        binding.checkInSearchButton.setOnClickListener{

            var searchItem:String = binding.searchCheckIn.text.toString()

            if(searchItem != ""){
                searchItem = searchItem.toUpperCase()

                reservationDatabaseViewModel.searchTodaysCheckIn("%" + searchItem + "%").observe(viewLifecycleOwner, Observer { reservataion ->
                    adapter.setData(reservataion)
                })
            }else{
                reservationDatabaseViewModel.todaysCheckIn.observe(viewLifecycleOwner, Observer { reservation ->
                    adapter.setData(reservation)
                })
            }
        }

        return binding.root
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