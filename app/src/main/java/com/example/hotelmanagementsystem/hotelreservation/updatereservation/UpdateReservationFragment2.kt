package com.example.hotelmanagementsystem.hotelreservation.updatereservation

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
import com.example.hotelmanagementsystem.R
import com.example.hotelmanagementsystem.database.reservations.Reservation
import com.example.hotelmanagementsystem.databinding.FragmentUpdateReservation2Binding
import com.example.hotelmanagementsystem.hotelreservation.ReservationDetailFragmentArgs
import com.example.hotelmanagementsystem.hotelreservation.viewmodel.ReservationDatabaseViewModel
import com.example.hotelmanagementsystem.hotelreservation.viewmodel.ReservationViewModel


class UpdateReservationFragment2 : Fragment() {

    private val sharedViewModel: ReservationViewModel by activityViewModels()
    private lateinit var reservationDatabaseViewModel: ReservationDatabaseViewModel
    private val args by navArgs<ReservationDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentUpdateReservation2Binding>(inflater,
            R.layout.fragment_update_reservation2,container,false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Update Reservation"

        // Initiate viewmodel
        reservationDatabaseViewModel = ViewModelProvider(this).get(ReservationDatabaseViewModel::class.java)

        // Set value from sharedViewModel into text field
        setValueToTextField(binding)

        binding.saveBtn.setOnClickListener{
            saveButtonOnclick(it, binding)
        }

        return binding.root

    }

    fun setValueToTextField(binding : FragmentUpdateReservation2Binding){

        binding.txtResitNo.setText(sharedViewModel.referenceNo)

    }

    fun saveButtonOnclick(view : View, binding: FragmentUpdateReservation2Binding){

        var grandTotal:String = binding.txtTotalPrice.text.toString()
        var paymentMethod:String = binding.spinnerPaymentMethod.selectedItem.toString()
        var referenceNo:String = binding.txtResitNo.text.toString()

        sharedViewModel.setReferenceNo(referenceNo)
        sharedViewModel.setPaymentMethod(paymentMethod)
        sharedViewModel.setTotalAmount(grandTotal)

        // update data into the database
        updateDatabase()


    }

    fun updateDatabase(){
        var reservationID:Int = sharedViewModel.reservationID.toInt()
        var name = sharedViewModel.guestName.toString()
        var idType = sharedViewModel.idType.toString()
        var idNo = sharedViewModel.IDNo.toString()
        var roomType = sharedViewModel.roomType.toString()
        var checkInDate = sharedViewModel.checkInDate.toString()
        var checkOutDate = sharedViewModel.checkOutDate.toString()
        var guestEmail = sharedViewModel.email.toString()
        var guestPhoneNo = sharedViewModel.phoneNo.toString()
        var paymentMethod = sharedViewModel.paymentMethod.toString()
        var referenceNo = sharedViewModel.referenceNo.toString()
        var status = "pending"
        var roomId = "R0000"


        // Create Reservation object
        val updateReservation = Reservation(reservationID, name, idType, idNo, roomType.toString(), checkInDate, checkOutDate, guestEmail, guestPhoneNo, paymentMethod, referenceNo, status, roomId)

        // Update Current Reservation Details
        reservationDatabaseViewModel.updateReservation(updateReservation)

        // Showing Toast message to user
        Toast.makeText(requireContext(), "Reservation Edited", Toast.LENGTH_LONG).show()

        // Navigate to reservation main menu
        findNavController().navigate(R.id.action_updateReservationFragment2_to_reservationMenuFragment)

    }

}