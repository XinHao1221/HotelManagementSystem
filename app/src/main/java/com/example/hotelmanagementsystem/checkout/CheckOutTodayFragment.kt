package com.example.hotelmanagementsystem.checkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotelmanagementsystem.R
import com.example.hotelmanagementsystem.checkin.recycleview.CheckInAdapter
import com.example.hotelmanagementsystem.checkout.recycleview.CheckOutAdapter
import com.example.hotelmanagementsystem.databinding.FragmentCheckOutMenuBinding
import com.example.hotelmanagementsystem.databinding.FragmentCheckOutTodayBinding
import com.example.hotelmanagementsystem.hotelreservation.viewmodel.ReservationDatabaseViewModel
import kotlinx.android.synthetic.main.fragment_check_in_menu.view.*


class CheckOutTodayFragment : Fragment() {

    private lateinit var reservationDatabaseViewModel: ReservationDatabaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentCheckOutTodayBinding>(inflater,
            R.layout.fragment_check_out_today,container,false)

        // Recycle view
        val adapter = CheckOutAdapter()
        val recyclerView = binding.checkOutTodayRecyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // ReservationViewModel
        reservationDatabaseViewModel = ViewModelProvider(this).get(ReservationDatabaseViewModel::class.java)
        reservationDatabaseViewModel.todaysCheckOut.observe(viewLifecycleOwner, Observer { reservation ->
            adapter.setData(reservation)
        })

        binding.checkOutTodayImageButton.setOnClickListener{

            var searchItem:String = binding.searchCheckOutToday.text.toString()

            if(searchItem != ""){
                searchItem = searchItem.toUpperCase()

                reservationDatabaseViewModel.searchTodaysCheckOut("%" + searchItem + "%").observe(viewLifecycleOwner, Observer { reservataion ->
                    adapter.setData(reservataion)
                })
            }else{
                reservationDatabaseViewModel.todaysCheckOut.observe(viewLifecycleOwner, Observer { reservation ->
                    adapter.setData(reservation)
                })
            }
        }

        return binding.root
    }

}