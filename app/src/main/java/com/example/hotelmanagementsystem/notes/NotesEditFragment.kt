package com.example.hotelmanagementsystem.notes

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hotelmanagementsystem.R
import com.example.hotelmanagementsystem.database.NotesDB.NotesEntity
import com.example.hotelmanagementsystem.database.Reservation
import com.example.hotelmanagementsystem.databinding.FragmentNotesDetailsBinding
import com.example.hotelmanagementsystem.databinding.FragmentNotesEditBinding
import com.example.hotelmanagementsystem.databinding.FragmentUpdateReservation2Binding
import com.example.hotelmanagementsystem.notes.viewmodel.NotesDatabaseViewModel
import com.example.hotelmanagementsystem.notes.viewmodel.NotesViewModel

class NotesEditFragment : Fragment() {

    private val sharedViewModel: NotesViewModel by activityViewModels()
    private val args by navArgs<NotesDetailsFragmentArgs>()
    private lateinit var notesDatabaseViewModel: NotesDatabaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentNotesEditBinding>(inflater,
            R.layout.fragment_notes_edit, container,false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Edit Notes"

        // Initiate viewmodel
        notesDatabaseViewModel = ViewModelProvider(this).get(NotesDatabaseViewModel::class.java)

        // Set value from sharedViewModel into text field
        setValueToTextField(binding)

        // Save button
        binding.notesEditSaveBtn.setOnClickListener{
            saveNotesOnclick(it, binding)
        }


        return binding.root
    }

    fun setValueToTextField(binding : FragmentNotesEditBinding){

        binding.notesEditTitle.setText(sharedViewModel.notesTitle)
        binding.notesEditDesc.setText(sharedViewModel.notesDesc)

    }

    private fun saveNotesOnclick(view: View, binding : FragmentNotesEditBinding){

        // Variable declaration
        var notesID:String = binding.notesEditID.text.toString()
        var notesTitle:String = binding.notesEditTitle.text.toString()
        var notesDesc:String = binding.notesEditDesc.text.toString()


        if(notesTitle.toString() == ""){
            DrawableCompat.setTint(binding.notesEditTitle.background, Color.RED)
            binding.notesEditDesc.setError("This field is required!")
        }
        else{

            val builder = AlertDialog.Builder(requireContext())

            builder.setNegativeButton("No"){_, _ ->

            }
            // Confirm save
            builder.setPositiveButton("Yes"){_, _->
                sharedViewModel.setNotesID(notesID)
                sharedViewModel.setNotesTitle(notesTitle)
                sharedViewModel.setNotesDesc(notesDesc)

                insertDataToDatabase()
            }

            builder.setTitle("Save?");

            builder.setMessage("Confirm Save?")
            builder.create().show()
        }


    }

    private fun insertDataToDatabase(){

        var notesTitle = sharedViewModel.notesTitle.toString()
        var notesDesc = sharedViewModel.notesTitle.toString()

        // Create Notes object
        val notes = NotesEntity(0, notesTitle, notesDesc)

        notesDatabaseViewModel.addNotes(notes)

        Toast.makeText(requireContext(), "Notes Saved", Toast.LENGTH_LONG).show()
    }
}

