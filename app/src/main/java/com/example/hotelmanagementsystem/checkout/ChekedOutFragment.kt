package com.example.hotelmanagementsystem.checkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotelmanagementsystem.R
import com.example.hotelmanagementsystem.checkout.recycleview.CheckOutAdapter
import com.example.hotelmanagementsystem.checkout.recycleview.TotalCheckOutAdapter
import com.example.hotelmanagementsystem.databinding.FragmentCheckOutTodayBinding
import com.example.hotelmanagementsystem.databinding.FragmentGuestsInHouseBinding
import com.example.hotelmanagementsystem.databinding.FragmentGuestsInHouseBindingImpl
import com.example.hotelmanagementsystem.hotelreservation.viewmodel.ReservationDatabaseViewModel


class ChekedOutFragment : Fragment() {

    private lateinit var reservationDatabaseViewModel: ReservationDatabaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentGuestsInHouseBinding>(inflater,
            R.layout.fragment_guests_in_house,container,false)

        // Recycle view
        val adapter = TotalCheckOutAdapter()
        val recyclerView = binding.guestsInHouseRecyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // ReservationViewModel
        reservationDatabaseViewModel = ViewModelProvider(this).get(ReservationDatabaseViewModel::class.java)
        reservationDatabaseViewModel.allCheckOut.observe(viewLifecycleOwner, Observer { reservation ->
            adapter.setData(reservation)
        })

        binding.imageButton.setOnClickListener{

            var searchItem:String = binding.searchGuestsInHouse.text.toString()

            if(searchItem != ""){
                searchItem = searchItem.toUpperCase()

                reservationDatabaseViewModel.searchReservation("%" + searchItem + "%", "checkOut").observe(viewLifecycleOwner, Observer { reservataion ->
                    adapter.setData(reservataion)
                })
            }else{
                reservationDatabaseViewModel.allCheckOut.observe(viewLifecycleOwner, Observer { reservation ->
                    adapter.setData(reservation)
                })
            }
        }

        return binding.root
    }

}