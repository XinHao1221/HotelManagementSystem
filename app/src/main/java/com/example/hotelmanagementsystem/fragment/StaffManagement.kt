package com.example.hotelmanagementsystem.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotelmanagementsystem.R
import com.example.hotelmanagementsystem.database.staffs.StaffViewModel
import com.example.hotelmanagementsystem.fragment.list.StaffListAdapter
import kotlinx.android.synthetic.main.fragment_staff_management.*

class StaffManagement : Fragment() {
    private lateinit var rStaffViewModel: StaffViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_staff_management, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Recyclerview
        val adapter= StaffListAdapter()
        val recyclerView = recyclerview3
        recyclerView.adapter = adapter
        recyclerView.layoutManager= LinearLayoutManager(requireContext())
        //找到控件
        rStaffViewModel = ViewModelProvider(this).get(StaffViewModel::class.java)
        rStaffViewModel.readAllData.observe(viewLifecycleOwner, Observer { staff ->
            adapter.setData(staff)
        })
    }
}