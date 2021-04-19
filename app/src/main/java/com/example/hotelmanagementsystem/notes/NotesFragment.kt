package com.example.hotelmanagementsystem.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotelmanagementsystem.R
import com.example.hotelmanagementsystem.notes.viewmodel.NotesDatabaseViewModel
import com.example.hotelmanagementsystem.notes.viewmodel.NotesViewModel
import com.example.hotelmanagementsystem.databinding.FragmentNotesBinding

class NotesFragment : Fragment() {

    private lateinit var notesDatabaseViewModel: NotesDatabaseViewModel
    private val sharedViewModel: NotesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentNotesBinding>(inflater,
            R.layout.fragment_notes,container,false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Notes"

        // Reset the sharedViewModel to empty
        clearSharedViewModel()

        // Recycle view
        val adapter = NotesAdapter()
        val recyclerView = binding.notesRecyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //NotesViewModel
        notesDatabaseViewModel = ViewModelProvider(this).get(NotesDatabaseViewModel::class.java)
        notesDatabaseViewModel.readAllData.observe(viewLifecycleOwner, Observer { notes ->
            adapter.setData(notes)
        })

        binding.addNotesFloatBtn.setOnClickListener {
            findNavController().navigate(R.id.action_notesFragment_to_notesAddFragment)
        }


        return binding.root
    }

        // Function to clear content inside sharedViewModel
        fun clearSharedViewModel(){
            sharedViewModel.setNotesID("")
            sharedViewModel.setNotesTitle("")
            sharedViewModel.setNotesDesc("")
        }
}