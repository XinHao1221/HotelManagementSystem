package com.example.hotelmanagementsystem.hotelreservation.addreservation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.*
import androidx.navigation.fragment.findNavController
import com.example.hotelmanagementsystem.R
import com.example.hotelmanagementsystem.databinding.FragmentDisplayReservationSummaryBinding
import com.example.hotelmanagementsystem.hotelreservation.viewmodel.ReservationViewModel


class DisplayReservationSummaryFragment : Fragment() {

    private val sharedViewModel: ReservationViewModel by activityViewModels()
    private var binding: FragmentDisplayReservationSummaryBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentDisplayReservationSummaryBinding>(inflater,
            R.layout.fragment_display_reservation_summary,container,false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Make Reservation"

        binding.goToPaymentPage.setOnClickListener {
            findNavController().navigate(R.id.action_displayReservationSummaryFragment_to_addReservation2Fragment)
        }

        binding.editReservationDetailsBtn.setOnClickListener {
            findNavController().navigate(R.id.action_displayReservationSummaryFragment_to_addReservation1Fragment)
        }

        //return view;

        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            displayReservationSummaryFragment = this@DisplayReservationSummaryFragment
        }

    }


}