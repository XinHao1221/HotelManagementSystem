package com.example.hotelmanagementsystem.checkin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hotelmanagementsystem.R
import com.example.hotelmanagementsystem.databinding.FragmentCheckInDetailBinding
import com.example.hotelmanagementsystem.databinding.FragmentCheckInDetailBindingImpl
import com.example.hotelmanagementsystem.databinding.FragmentReservationDetailBinding
import com.example.hotelmanagementsystem.databinding.FragmentUpdateReservation1Binding
import com.example.hotelmanagementsystem.hotelreservation.ReservationDetailFragmentArgs
import com.example.hotelmanagementsystem.hotelreservation.viewmodel.ReservationDatabaseViewModel
import com.example.hotelmanagementsystem.hotelreservation.viewmodel.ReservationViewModel


class CheckInDetailFragment : Fragment() {

    // Test
    private val sharedViewModel: ReservationViewModel by activityViewModels()
    private val args by navArgs<CheckInDetailFragmentArgs>()
    private lateinit var reservationDatabaseViewModel: ReservationDatabaseViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentCheckInDetailBinding>(inflater,
            R.layout.fragment_check_in_detail,container,false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Check In"

        // Set all value inside args into sharedViewModel
        setArgsValueToSharedViewModel()

        // Set data from sharedviewmodel into textview
        displayReservationDetails(binding)

        binding.checkInNextButton.setOnClickListener{
            findNavController().navigate(R.id.action_checkInDetailFragment_to_selectRoomFragment)
        }


        return binding.root
    }

    fun displayReservationDetails(binding : FragmentCheckInDetailBinding){

        var reservationId = formatReservationID()
        binding.viewReservationId.setText(reservationId)

        binding.viewReservationName.setText(sharedViewModel.guestName)

        if(sharedViewModel.phoneNo.toString() == "")
            binding.viewPhoneNo.setText("-")
        else
            binding.viewPhoneNo.setText(sharedViewModel.phoneNo)

        if(sharedViewModel.email.toString() == "")
            binding.viewEmail.setText("-")
        else
            binding.viewEmail.setText(sharedViewModel.email)


        binding.viewRoomType.setText(sharedViewModel.roomType)

        var durationOfStay:String = sharedViewModel.checkInDate + " - " + sharedViewModel.checkOutDate
        binding.viewDurationStay.setText(durationOfStay)

        if(sharedViewModel.IDNo.toString() == "")
            binding.viewIdNo.setText("-")
        else
            binding.viewIdNo.setText(sharedViewModel.IDNo)

        binding.viewIdType.setText(sharedViewModel.idType)

        binding.viewPaymentMethod.setText(sharedViewModel.paymentMethod)

        if(sharedViewModel.referenceNo.toString() == "")
            binding.viewReferenceNo.setText("-")
        else
            binding.viewReferenceNo.setText(sharedViewModel.referenceNo)

    }

    fun formatReservationID():String{

        var reservationID = sharedViewModel.reservationID.toString()

        var id:Int = 100000 + reservationID.toInt()

        reservationID = "R" + id.toString()

        return reservationID.toString()
    }

    fun setArgsValueToSharedViewModel(){

        // Set all the value for Reservation args into shared view model
        sharedViewModel.setGuestName(args.currentCheckIn.guestName.toString())
        sharedViewModel.setIdType(args.currentCheckIn.idType.toString())
        sharedViewModel.setIDNo(args.currentCheckIn.idNo.toString())
        sharedViewModel.setRoomType(args.currentCheckIn.roomType.toString())
        sharedViewModel.setCheckInDate(args.currentCheckIn.checkInDate.toString())
        sharedViewModel.setCheckOutDate(args.currentCheckIn.checkOutDate.toString())
        sharedViewModel.setEmail(args.currentCheckIn.guestEmail.toString())
        sharedViewModel.setPhoneNo(args.currentCheckIn.guestPhoneNo.toString())
        sharedViewModel.setPaymentMethod(args.currentCheckIn.paymentMethod.toString())
        sharedViewModel.setReferenceNo(args.currentCheckIn.referenceNo.toString())
        sharedViewModel.setStatus(args.currentCheckIn.status.toString())
        sharedViewModel.setRoomID(args.currentCheckIn.roomId.toString())
        sharedViewModel.setReservationID(args.currentCheckIn.reservationId.toString())
    }




}