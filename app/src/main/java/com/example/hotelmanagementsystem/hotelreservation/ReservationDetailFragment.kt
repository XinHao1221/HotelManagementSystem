package com.example.hotelmanagementsystem.hotelreservation

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.room.Update
import com.example.hotelmanagementsystem.R
import com.example.hotelmanagementsystem.database.Reservation
import com.example.hotelmanagementsystem.databinding.FragmentAddReservation1Binding
import com.example.hotelmanagementsystem.databinding.FragmentReservationDetailBinding
import com.example.hotelmanagementsystem.hotelreservation.viewmodel.ReservationDatabaseViewModel
import com.example.hotelmanagementsystem.hotelreservation.viewmodel.ReservationViewModel


class ReservationDetailFragment : Fragment() {

    private val args by navArgs<ReservationDetailFragmentArgs>()
    private val sharedViewModel: ReservationViewModel by activityViewModels()
    private lateinit var reservationDatabaseViewModel: ReservationDatabaseViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentReservationDetailBinding>(inflater,
            R.layout.fragment_reservation_detail,container,false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Reservation"

        // Initiate viewmodel
        reservationDatabaseViewModel = ViewModelProvider(this).get(ReservationDatabaseViewModel::class.java)

        // Set all value inside args into sharedViewModel
        setArgsValueToSharedViewModel()

        // Set data from sharedviewmodel into textview
        displayReservationDetails(binding)

        binding.reservationDetailsNextBtn.setOnClickListener{
            findNavController().navigate(R.id.action_reservationDetailFragment_to_updateReservationFragment1)
        }

        binding.reservationDetailsDeleteBtn.setOnClickListener{
            deleteReservation()
        }

        return binding.root
    }


    fun displayReservationDetails(binding : FragmentReservationDetailBinding){

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

    fun setArgsValueToSharedViewModel(){

        // Set all the value for Reservation args into shared view model
        sharedViewModel.setGuestName(args.currentReservation.guestName.toString())
        sharedViewModel.setIdType(args.currentReservation.idType.toString())
        sharedViewModel.setIDNo(args.currentReservation.idNo.toString())
        sharedViewModel.setRoomType(args.currentReservation.roomType.toString())
        sharedViewModel.setCheckInDate(args.currentReservation.checkInDate.toString())
        sharedViewModel.setCheckOutDate(args.currentReservation.checkOutDate.toString())
        sharedViewModel.setEmail(args.currentReservation.guestEmail.toString())
        sharedViewModel.setPhoneNo(args.currentReservation.guestPhoneNo.toString())
        sharedViewModel.setPaymentMethod(args.currentReservation.paymentMethod.toString())
        sharedViewModel.setReferenceNo(args.currentReservation.referenceNo.toString())
        sharedViewModel.setStatus(args.currentReservation.status.toString())
        sharedViewModel.setRoomID(args.currentReservation.roomId.toString())
        sharedViewModel.setReservationID(args.currentReservation.reservationId.toString())
    }

    private fun deleteReservation(){

        val builder = AlertDialog.Builder(requireContext())

        builder.setNegativeButton("No"){_, _ ->

        }
        // Confirm delete
        builder.setPositiveButton("Yes"){_, _->
            reservationDatabaseViewModel.deleteReservation(args.currentReservation)
            Toast.makeText(requireContext(), "Successfully Deleted!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_reservationDetailFragment_to_reservationMenuFragment)
        }

        builder.setTitle("Delete?");

        builder.setMessage("Confirm Delete?")
        builder.create().show()
    }

    fun formatReservationID():String{

        var reservationID = sharedViewModel.reservationID.toString()

        var id:Int = 100000 + reservationID.toInt()

        reservationID = "R" + id.toString()

        return reservationID.toString()
    }

}