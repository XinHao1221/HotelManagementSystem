package com.example.hotelmanagementsystem.checkout

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
import com.example.hotelmanagementsystem.databinding.FragmentCheckOutDetailBinding
import com.example.hotelmanagementsystem.databinding.FragmentCheckedOutDetailBinding
import com.example.hotelmanagementsystem.hotelreservation.viewmodel.ReservationDatabaseViewModel
import com.example.hotelmanagementsystem.hotelreservation.viewmodel.ReservationViewModel


class CheckedOutDetailFragment : Fragment() {

    private val sharedViewModel: ReservationViewModel by activityViewModels()
    private val args by navArgs<CheckedOutDetailFragmentArgs>()
    private lateinit var reservationDatabaseViewModel: ReservationDatabaseViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentCheckedOutDetailBinding>(inflater,
            R.layout.fragment_checked_out_detail,container,false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Check out"

        // Set all value inside args into sharedViewModel
        setArgsValueToSharedViewModel()

        // Set data from sharedviewmodel into textview
        displayReservationDetails(binding)

        // Close showing checked out detail
        binding.checkedOutCloseButton.setOnClickListener{
            findNavController().navigate(R.id.action_checkedOutDetailFragment_to_checkOutMenuFragment)
        }

        return binding.root
    }

    fun displayReservationDetails(binding : FragmentCheckedOutDetailBinding){

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
        sharedViewModel.setGuestName(args.currentCheckedOut.guestName.toString())
        sharedViewModel.setIdType(args.currentCheckedOut.idType.toString())
        sharedViewModel.setIDNo(args.currentCheckedOut.idNo.toString())
        sharedViewModel.setRoomType(args.currentCheckedOut.roomType.toString())
        sharedViewModel.setCheckInDate(args.currentCheckedOut.checkInDate.toString())
        sharedViewModel.setCheckOutDate(args.currentCheckedOut.checkOutDate.toString())
        sharedViewModel.setEmail(args.currentCheckedOut.guestEmail.toString())
        sharedViewModel.setPhoneNo(args.currentCheckedOut.guestPhoneNo.toString())
        sharedViewModel.setPaymentMethod(args.currentCheckedOut.paymentMethod.toString())
        sharedViewModel.setReferenceNo(args.currentCheckedOut.referenceNo.toString())
        sharedViewModel.setStatus(args.currentCheckedOut.status.toString())
        sharedViewModel.setRoomID(args.currentCheckedOut.roomId.toString())
        sharedViewModel.setReservationID(args.currentCheckedOut.reservationId.toString())
    }

}