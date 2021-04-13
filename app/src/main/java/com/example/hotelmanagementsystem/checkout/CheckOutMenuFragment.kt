package com.example.hotelmanagementsystem.checkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.hotelmanagementsystem.R
import com.example.hotelmanagementsystem.databinding.FragmentCheckOutMenuBinding
import com.example.hotelmanagementsystem.hotelreservation.viewmodel.ReservationDatabaseViewModel
import com.example.hotelmanagementsystem.hotelreservation.viewmodel.ReservationViewModel


class CheckOutMenuFragment : Fragment() {

    private lateinit var reservationDatabaseViewModel: ReservationDatabaseViewModel
    private val sharedViewModel: ReservationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentCheckOutMenuBinding>(inflater,
            R.layout.fragment_check_out_menu,container,false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Check Out"

        // Reset the sharedViewModel to empty
        clearSharedViewModel()

        // COde to add three fragment into a tab
        setUpTabs(binding)

        return binding.root
    }

    private fun setUpTabs(binding : FragmentCheckOutMenuBinding) {
        var viewPager:CheckOutViewPagerAdapter

        val pagerAdapter = CheckOutViewPagerAdapter(childFragmentManager)
        pagerAdapter.addFragment(CheckOutTodayFragment(), "Check Out Today")
        pagerAdapter.addFragment(ChekedOutFragment(), "Checked Out")

        binding.checkOutViewPager.adapter = pagerAdapter
        binding.checkOutTabLayout.setupWithViewPager(binding.checkOutViewPager)
    }

    fun clearSharedViewModel(){
        sharedViewModel.setGuestName("")
        sharedViewModel.setIdType("")
        sharedViewModel.setIDNo("")
        sharedViewModel.setRoomType("")
        sharedViewModel.setCheckInDate("")
        sharedViewModel.setCheckOutDate("")
        sharedViewModel.setEmail("")
        sharedViewModel.setPhoneNo("")
        sharedViewModel.setTotalAmount("")
        sharedViewModel.setPaymentMethod("")
        sharedViewModel.setReferenceNo("")
    }

}