package com.example.hotelmanagementsystem.hotelreservation.addreservation

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
import com.example.hotelmanagementsystem.R
import com.example.hotelmanagementsystem.database.reservations.Reservation
import com.example.hotelmanagementsystem.databinding.FragmentAddReservation2Binding
import com.example.hotelmanagementsystem.hotelreservation.viewmodel.ReservationDatabaseViewModel
import com.example.hotelmanagementsystem.hotelreservation.viewmodel.ReservationViewModel


class AddReservation2Fragment : Fragment() {

    private val sharedViewModel: ReservationViewModel by activityViewModels()
    private lateinit var reservationDatabaseViewModel: ReservationDatabaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentAddReservation2Binding>(
            inflater, R.layout.fragment_add_reservation2, container, false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Make Reservation"

        reservationDatabaseViewModel = ViewModelProvider(this).get(ReservationDatabaseViewModel::class.java)

        binding.saveBtn.setOnClickListener {
            saveButtonOnclick(it, binding)

            //Toast.makeText(requireContext(), "SuccessAdded", Toast.LENGTH_LONG).show()

            //findNavController().navigate(R.id.action_addReservation2Fragment_to_addReservation1Fragment)
        }

        return binding.root
    }



    fun saveButtonOnclick(view : View, binding:FragmentAddReservation2Binding){


        var grandTotal:String = binding.txtTotalPrice.text.toString()
        var paymentMethod:String = binding.spinnerPaymentMethod.selectedItem.toString()
        var referenceNo:String = binding.txtResitNo.text.toString()

        sharedViewModel.setReferenceNo(referenceNo)
        sharedViewModel.setPaymentMethod(paymentMethod)
        sharedViewModel.setTotalAmount(grandTotal)

        insertDataToDatabase()



    }

    private fun insertDataToDatabase(){

        //val binding:FragmentAddReservation2Binding? = null

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
        val reservation = Reservation(0, name, idType, idNo, roomType.toString(), checkInDate, checkOutDate, guestEmail, guestPhoneNo, paymentMethod, referenceNo, status, roomId)

        reservationDatabaseViewModel.addReservation(reservation)

        Toast.makeText(requireContext(), "Reservation Added", Toast.LENGTH_LONG).show()

        findNavController().navigate(R.id.action_addReservation2Fragment_to_reservationMenuFragment)
    }
}