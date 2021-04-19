package com.example.hotelmanagementsystem.hotelreservation.addreservation

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hotelmanagementsystem.R
import com.example.hotelmanagementsystem.databinding.FragmentAddReservation1Binding
import com.example.hotelmanagementsystem.hotelreservation.viewmodel.ReservationDatabaseViewModel
import com.example.hotelmanagementsystem.hotelreservation.viewmodel.ReservationViewModel
import com.example.hotelmanagementsystem.test
import kotlinx.android.synthetic.main.fragment_add_reservation1.*

const val KEY_GUESTNAME = "guest_name"

class AddReservation1Fragment : Fragment() {

    private lateinit var reservationDatabaseViewModel: ReservationDatabaseViewModel
    private val sharedViewModel: ReservationViewModel by activityViewModels()

    private var binding: FragmentAddReservation1Binding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.inflate<FragmentAddReservation1Binding>(inflater,
            R.layout.fragment_add_reservation1,container,false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Make Reservation"

        // ReservationViewModel
        reservationDatabaseViewModel = ViewModelProvider(this).get(ReservationDatabaseViewModel::class.java)

        if(savedInstanceState != null){
            binding.name.setText(savedInstanceState.getString(KEY_GUESTNAME), TextView.BufferType.EDITABLE)
        }

        // Set back user's entered value when user navigate back
        if(sharedViewModel != null){
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

        binding.reservationNextButton.setOnClickListener{
            nextButtonOnclick(it, binding)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            addReservationFragment = this@AddReservation1Fragment
        }


    }

    fun nextButtonOnclick(view : View, binding:FragmentAddReservation1Binding){

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

        // Check if check in date is less than or equals to check out date
//        var checkInDay = checkInDate.substring(0, 2)
//        var checkInMonth = checkInDate.substring(3, 5)
//        var checkInYear = checkInDate.substring(6, 10)
//
//        var checkOutDay = checkOutDate.substring(0, 2)
//        var checkOutMonth = checkOutDate.substring(3, 5)
//        var checkOutYear = checkOutDate.substring(6, 10)

//        if(checkOutDay.toInt() < checkInDate.toInt()){
//            binding.checkInDate.setTextColor(Color.GREEN)
//            binding.checkInDate.setText("Check In")
//            binding.checkInDate.setError("This field is required!")
//        }


        // If user have entered all the required page, navigate to next page
        if(condition == 1){

//            val _score = MutableLiveData<Int>()
//            val score: LiveData<Int>
//
//            reservationDatabaseViewModel.checkAvailability(sharedViewModel.checkInDate.toString(), sharedViewModel.roomType.toString()).observe(viewLifecycleOwner, Observer {
//                temp -> _score.value = temp
//
//                binding.textView.setText(_score.value.toString())
//
//            })

            findNavController().navigate(R.id.action_addReservation1Fragment_to_displayReservationSummaryFragment)
        }


        //Navigation.findNavController(view).navigate(R.id.action_addReservation1Fragment_to_test)


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString(KEY_GUESTNAME, binding?.name?.text.toString())

    }

    override fun onDestroy() {
        super.onDestroy()


    }





}

