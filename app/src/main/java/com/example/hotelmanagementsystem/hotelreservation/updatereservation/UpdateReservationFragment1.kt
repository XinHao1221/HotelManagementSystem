package com.example.hotelmanagementsystem.hotelreservation.updatereservation

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.hotelmanagementsystem.R
import com.example.hotelmanagementsystem.databinding.FragmentAddReservation1Binding
import com.example.hotelmanagementsystem.databinding.FragmentUpdateReservation1Binding
import com.example.hotelmanagementsystem.databinding.FragmentUpdateReservation2Binding
import com.example.hotelmanagementsystem.hotelreservation.viewmodel.ReservationViewModel


class UpdateReservationFragment1 : Fragment() {

    private val sharedViewModel: ReservationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentUpdateReservation1Binding>(inflater,
            R.layout.fragment_update_reservation1,container,false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Update Reservation"

        // Set value into textfield
        setValueToTextField(binding)

        binding.updateReservationNextButton.setOnClickListener{
            nextButtonOnclick(it, binding)
        }

        return return binding.root
    }

    fun setValueToTextField(binding:FragmentUpdateReservation1Binding){

        binding.name.setText(sharedViewModel.guestName)
        binding.txtIdNo.setText(sharedViewModel.IDNo)
        binding.checkInDate.text = sharedViewModel.checkInDate
        binding.checkOutDate.text = sharedViewModel.checkOutDate
        binding.txtEmail.setText(sharedViewModel.email)
        binding.txtPhoneNo.setText(sharedViewModel.phoneNo)

        // Set user selected spinner option
        // ID type
        if(sharedViewModel.idType == "My Card"){
            binding.spinnerIdType.setSelection(0)
        }else if(sharedViewModel.idType == "Passport"){
            binding.spinnerIdType.setSelection(1)
        }

        // Room Type
        if(sharedViewModel.roomType == "Single Room"){
            binding.spinnerRoomType.setSelection(0)
        }else if(sharedViewModel.roomType == "Double Room"){
            binding.spinnerRoomType.setSelection(1)
        }else if(sharedViewModel.roomType == "King Size Room"){
            binding.spinnerRoomType.setSelection(2)
        }else if(sharedViewModel.roomType == "Standard Room"){
            binding.spinnerRoomType.setSelection(3)
        }else if(sharedViewModel.roomType == "Triple Room"){
            binding.spinnerRoomType.setSelection(4)
        }else if(sharedViewModel.roomType == "Quad Room"){
            binding.spinnerRoomType.setSelection(5)
        }else if(sharedViewModel.roomType == "Deluxe Room"){
            binding.spinnerRoomType.setSelection(6)
        }
    }

    fun nextButtonOnclick(view : View, binding : FragmentUpdateReservation1Binding){

        // Change to 0 when one or more required field is empty
        var condition = 1

        // Variable declaration
        var guestName:String = binding.name.text.toString()
        var idType:String = binding.spinnerIdType.selectedItem.toString()
        var idNo:String = binding.txtIdNo.text.toString()
        var roomType:String = binding.spinnerRoomType.selectedItem.toString()
        var checkInDate:String = binding.checkInDate.text.toString()
        var checkOutDate:String = binding.checkOutDate.text.toString()
        var email:String = binding.txtEmail.text.toString()
        var phoneNo:String = binding.txtPhoneNo.text.toString()



//        var test = spr.count

//        for(i in 0..test){
//            if(spr.getItemAtPosition(i).equals(sharedViewModel.idType)){
//                //spr.setSelection(i)
//
//            }else{
//
//            }
//        }

        // Store the user input into sharedViewModel
        sharedViewModel.setGuestName(guestName)
        sharedViewModel.setIdType(idType)
        sharedViewModel.setIDNo(idNo)
        sharedViewModel.setRoomType(roomType)
        sharedViewModel.setCheckInDate(checkInDate)
        sharedViewModel.setCheckOutDate(checkOutDate)
        sharedViewModel.setEmail(email)
        sharedViewModel.setPhoneNo(phoneNo)
        //sharedViewModel.selectedPosition = (binding.spinnerIdType.selectedItemPosition)


        // Check if name, idNo, check in and check out date is empty
        if(guestName.toString() == ""){
            condition = 0
            DrawableCompat.setTint(binding.name.background, Color.RED)
            binding.name.setError("This field is required!")
        }else{

        }
        if(idNo.toString() == "") {
            condition = 0
            DrawableCompat.setTint(binding.txtIdNo.background, Color.RED)
            binding.txtIdNo.setError("This field is required!")
        }else{

        }
        if(checkInDate.toString() == ""){
            binding.checkInDate.setTextColor(Color.RED)
            binding.checkInDate.setText("Check In")
            binding.checkInDate.setError("This field is required!")
            condition = 0
            //DrawableCompat.setTint(binding.checkInDate.background, Color.RED)
        }else{

        }
        if(checkInDate.toString() == ""){
            binding.checkOutDate.setTextColor(Color.RED)
            binding.checkOutDate.setText("Check Out")
            binding.checkOutDate.setError("This field is required!")
            condition = 0
            //DrawableCompat.setTint(binding.checkInDate.background, Color.RED)
        }else{

        }

        // If user have entered all the required page, navigate to next page
        if(condition == 1)
            findNavController().navigate(R.id.action_updateReservationFragment1_to_updateReservationFragment2)

    }

}