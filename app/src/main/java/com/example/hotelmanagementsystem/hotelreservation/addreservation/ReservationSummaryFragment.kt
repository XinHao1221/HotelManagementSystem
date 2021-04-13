package com.example.hotelmanagementsystem.hotelreservation.addreservation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.hotelmanagementsystem.R
import com.example.hotelmanagementsystem.databinding.FragmentReservationSummaryBinding
import com.example.hotelmanagementsystem.hotelreservation.viewmodel.ReservationViewModel

class ReservationSummaryFragment : Fragment() {

    private val sharedViewModel: ReservationViewModel by activityViewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentReservationSummaryBinding>(inflater,
            R.layout.fragment_reservation_summary,container,false)

        displayReservationSummary(binding)

        return binding.root;
    }

    fun displayReservationSummary(binding:FragmentReservationSummaryBinding){
        binding.reservationName.setText(sharedViewModel.guestName)

        if(sharedViewModel.phoneNo.toString() == "")
            binding.phoneNo.setText("-")
        else
            binding.phoneNo.setText(sharedViewModel.phoneNo)

        if(sharedViewModel.email.toString() == "")
            binding.email.setText("-")
        else
            binding.email.setText(sharedViewModel.email)


        binding.roomType.setText(sharedViewModel.roomType)

        var durationOfStay:String = sharedViewModel.checkInDate + " - " + sharedViewModel.checkOutDate
        binding.durationStay.setText(durationOfStay)

        if(sharedViewModel.IDNo.toString() == "")
            binding.idNo.setText("-")
        else
            binding.idNo.setText(sharedViewModel.IDNo)

        binding.idType.setText(sharedViewModel.idType)
    }


}