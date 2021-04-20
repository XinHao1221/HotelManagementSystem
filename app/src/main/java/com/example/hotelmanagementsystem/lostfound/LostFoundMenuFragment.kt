package com.example.hotelmanagementsystem.lostfound

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
import com.example.hotelmanagementsystem.database.lostitems.LostFoundMenuHandler
import com.example.hotelmanagementsystem.database.lostitems.LostFoundMenuViewModel
import kotlinx.android.synthetic.main.fragment_lostfound_menu.view.*

class LostfoundMenuFragment : Fragment() {

    private lateinit var lostFoundMenuViewModel: LostFoundMenuViewModel
    private val sharedViewModel: LostFoundMenuHandler by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{

        val view = inflater.inflate(R.layout.fragment_lostfound_menu, container, false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Lost & Found"

        // Reset the sharedViewModel to empty
        clearSharedViewModel()

        // Recycle view
        val adapter = LostItemAdapter()
        val recyclerView = view.recycle_lost_items
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // LostFoundMenuViewModel
        lostFoundMenuViewModel = ViewModelProvider(this).get(LostFoundMenuViewModel::class.java)
        lostFoundMenuViewModel.allLostItems.observe(viewLifecycleOwner, Observer { lostItem ->
            adapter.data = lostItem
        })

        view.lost_item_num.text = "-"

        view.new_lost_item_btn.setOnClickListener {
            findNavController().navigate(R.id.action_lostfoundMenuFragment_to_addLostItemFragment)
        }

        return view
    }

    // Function to clear content inside sharedViewModel
    private fun clearSharedViewModel(){
        sharedViewModel.setLostItemId(null)
        sharedViewModel.setItemImg(null)
        sharedViewModel.setLostLocation("")
        sharedViewModel.setDescription("")
        sharedViewModel.setFoundStatus(false)
        sharedViewModel.setFoundTime("")
    }
}