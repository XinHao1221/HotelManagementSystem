package com.example.hotelmanagementsystem.checkout

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hotelmanagementsystem.R
import com.example.hotelmanagementsystem.databinding.FragmentEquipmentCheckListBinding
import com.example.hotelmanagementsystem.databinding.FragmentSelectRoomBinding
import com.example.hotelmanagementsystem.hotelreservation.viewmodel.ReservationDatabaseViewModel
import com.example.hotelmanagementsystem.hotelreservation.viewmodel.ReservationViewModel


class EquipmentCheckListFragment : Fragment() {

    private val sharedViewModel: ReservationViewModel by activityViewModels()
    private lateinit var reservationDatabaseViewModel: ReservationDatabaseViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentEquipmentCheckListBinding>(inflater,
            R.layout.fragment_equipment_check_list,container,false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Check Out"

        // ReservationViewModel
        reservationDatabaseViewModel = ViewModelProvider(this).get(ReservationDatabaseViewModel::class.java)

        binding.checkOutBtn.setOnClickListener{
            checkIsEquipmentCheckListChecked(binding)
        }

        return binding.root
    }

    fun resetCheckBoxBackgroundColor(binding : FragmentEquipmentCheckListBinding){
        binding.checkboxAirCon.setBackgroundColor(Color.parseColor("#FFFFFF"))
        binding.checkboxHotelHandbook.setBackgroundColor(Color.parseColor("#FFFFFF"))
        binding.checkboxTvCtrl.setBackgroundColor(Color.parseColor("#FFFFFF"))
        binding.checkboxRoomAccessCard.setBackgroundColor(Color.parseColor("#FFFFFF"))
    }

    fun checkIsEquipmentCheckListChecked(binding : FragmentEquipmentCheckListBinding){

        resetCheckBoxBackgroundColor(binding)

        var isChecked:Int = 1
        if(!(binding.checkboxAirCon.isChecked)){
            binding.checkboxAirCon.setBackgroundColor(Color.parseColor("#ffdedb"))
            isChecked = 0
        }
        if(!(binding.checkboxHotelHandbook.isChecked)){
            binding.checkboxHotelHandbook.setBackgroundColor(Color.parseColor("#ffdedb"))
            isChecked = 0
        }
        if(!(binding.checkboxTvCtrl.isChecked)){
            binding.checkboxTvCtrl.setBackgroundColor(Color.parseColor("#ffdedb"))
            isChecked = 0
        }
        if(!(binding.checkboxRoomAccessCard.isChecked)){
            binding.checkboxRoomAccessCard.setBackgroundColor(Color.parseColor("#ffdedb"))
            isChecked = 0
        }

        if(isChecked == 0){

            val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Alert")
            builder.setMessage("Make sure you have checked all item in Equipment Check List")
            builder.setPositiveButton("OK"){_,_ ->}
            builder.show()

        }else{

            var reservationID:Int = sharedViewModel.reservationID.toInt()

            val builder = android.app.AlertDialog.Builder(requireContext())

            builder.setNegativeButton("No"){_, _ ->

            }
            // Confirm delete
            builder.setPositiveButton("Yes"){_, _->

                // Update reservation status to checkIn
                reservationDatabaseViewModel.updateReservationStatus("checkOut", reservationID)

                Toast.makeText(requireContext(), "Check Out Successfully", Toast.LENGTH_SHORT).show()

                findNavController().navigate(R.id.action_equipmentCheckListFragment_to_checkOutMenuFragment)
            }

            builder.setTitle("Check Out?");

            builder.setMessage("Confirm check out " + sharedViewModel.guestName + "?")
            builder.create().show()

        }
    }

}