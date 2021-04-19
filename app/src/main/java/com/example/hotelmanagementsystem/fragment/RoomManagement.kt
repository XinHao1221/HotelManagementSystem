package com.example.myapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotelmanagementsystem.R
import com.example.myapp.database.Floor
import com.example.myapp.database.HotelRoomViewModel
import com.example.myapp.fragment.list.RoomListAdapter
import kotlinx.android.synthetic.main.fragment_floor_management.*
import kotlinx.android.synthetic.main.fragment_room_management.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class RoomManagement : Fragment(),View.OnClickListener {
    private lateinit var rRoomViewModel: HotelRoomViewModel
    private lateinit var addButton: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_room_management, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Recyclerview
        val adapter= RoomListAdapter()
        val recyclerView = recyclerview2
        recyclerView.adapter = adapter
        recyclerView.layoutManager= LinearLayoutManager(requireContext())
        //找到控件
        rRoomViewModel = ViewModelProvider(this).get(HotelRoomViewModel::class.java)
        var floorID: String? = arguments?.getString("floorID")
        if (floorID != null) {
            rRoomViewModel.selectRoomWithFloor(floorID).observe(viewLifecycleOwner, Observer { room ->
                adapter.setData(room)
            })
        }
        addButton=view.findViewById(R.id.fab)
        addButton.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        var floorID: String? = arguments?.getString("floorID")
        val bundle = bundleOf("floorID" to floorID)
        findNavController().navigate(R.id.action_roomManagement_to_addRoom1, bundle)
    }
}
