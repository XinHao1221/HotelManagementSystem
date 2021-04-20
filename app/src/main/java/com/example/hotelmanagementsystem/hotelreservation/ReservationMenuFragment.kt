package com.example.hotelmanagementsystem.hotelreservation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotelmanagementsystem.R
import com.example.hotelmanagementsystem.hotelreservation.viewmodel.ReservationDatabaseViewModel
import com.example.hotelmanagementsystem.hotelreservation.viewmodel.ReservationViewModel
import kotlinx.android.synthetic.main.fragment_reservation_menu.view.*


class ReservationMenuFragment : Fragment() {

    private lateinit var reservationDatabaseViewModel: ReservationDatabaseViewModel
    private val sharedViewModel: ReservationViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View?{
//        val binding = DataBindingUtil.inflate<FragmentReservationMenuBinding>(
//            inflater, R.layout.fragment_reservation_menu, container, false)



        val view = inflater.inflate(R.layout.fragment_reservation_menu, container, false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Reservation"


        // Reset the sharedViewModel to empty
        clearSharedViewModel()

        // Recycle view
        val adapter = ReservationAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // ReservationViewModel
        reservationDatabaseViewModel = ViewModelProvider(this).get(ReservationDatabaseViewModel::class.java)
        reservationDatabaseViewModel.allPendingReservation.observe(viewLifecycleOwner, Observer { reservataion ->
            adapter.setData(reservataion)

        })

        view.imageButton.setOnClickListener{

            var searchItem:String = view.search_reservation.text.toString()

            if(searchItem != ""){
                searchItem = searchItem.toUpperCase()

                reservationDatabaseViewModel.searchReservation("%" + searchItem + "%", "pending").observe(viewLifecycleOwner, Observer { reservataion ->
                    adapter.setData(reservataion)
                })
            }else{
                reservationDatabaseViewModel.allPendingReservation.observe(viewLifecycleOwner, Observer { reservataion ->
                    adapter.setData(reservataion)

                })
            }

        }




        view.add_new_reservation.setOnClickListener{
            findNavController().navigate(R.id.action_reservationMenuFragment_to_addReservation1Fragment)
        }


        return view
    }

    // Function to clear content inside sharedViewModel
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