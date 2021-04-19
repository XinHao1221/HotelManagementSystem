package com.example.hotelmanagementsystem.notes

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hotelmanagementsystem.R
import com.example.hotelmanagementsystem.databinding.FragmentNotesDetailsBinding
import com.example.hotelmanagementsystem.databinding.FragmentReservationSummaryBinding
import com.example.hotelmanagementsystem.databinding.FragmentUpdateReservation1Binding
import com.example.hotelmanagementsystem.hotelreservation.viewmodel.ReservationDatabaseViewModel
import com.example.hotelmanagementsystem.notes.viewmodel.NotesDatabaseViewModel
import com.example.hotelmanagementsystem.notes.viewmodel.NotesViewModel

class NotesDetailsFragment : Fragment() {

    private val sharedViewModel: NotesViewModel by activityViewModels()
    private val args by navArgs<NotesDetailsFragmentArgs>()
    private lateinit var notesDatabaseViewModel: NotesDatabaseViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentNotesDetailsBinding>(inflater,
                R.layout.fragment_notes_details, container,false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Notes"

        // Initiate viewmodel
        notesDatabaseViewModel = ViewModelProvider(this).get(NotesDatabaseViewModel::class.java)

        // Display all value in sharedViewModel
        displayNotesDetails(binding)

        // Set all value inside args into sharedViewModel
        setValueToTextField(binding)

        // Save button
        binding.notesSaveBtn.setOnClickListener{
            saveNotesOnclick(it, binding)
        }


        return binding.root
    }


    fun setValueToTextField(binding: FragmentNotesDetailsBinding){

        binding.notesDetailsTitle.setText(sharedViewModel.notesTitle)
        binding.notesDetailsDesc.setText(sharedViewModel.notesDesc)

    }

    fun displayNotesDetails(binding: FragmentNotesDetailsBinding){
        binding.notesDetailsID.setText(sharedViewModel.notesID)
        binding.notesDetailsTitle.setText(sharedViewModel.notesTitle)

        if(sharedViewModel.notesDesc == "")
            binding.notesDetailsDesc.setText("Edit notes details here.")
        else
            binding.notesDetailsDesc.setText(sharedViewModel.notesDesc)
    }

    private fun saveNotesOnclick(view: View, binding : FragmentNotesDetailsBinding){

        // Variable declaration
        var notesTitle:String = binding.notesDetailsTitle.text.toString()
        var notesDesc:String = binding.notesDetailsDesc.text.toString()

        if(notesTitle.toString() == ""){
            DrawableCompat.setTint(binding.notesDetailsTitle.background, Color.RED)
            binding.notesDetailsDesc.setError("This field is required!")
        }
        else{

            val builder = AlertDialog.Builder(requireContext())

            builder.setNegativeButton("No"){_, _ ->

            }
            // Confirm save
            builder.setPositiveButton("Yes"){_, _->
                sharedViewModel.setNotesTitle(notesTitle)
                sharedViewModel.setNotesDesc(notesDesc)
            }

            builder.setTitle("Save?");

            builder.setMessage("Confirm Save?")
            builder.create().show()
        }


    }

}