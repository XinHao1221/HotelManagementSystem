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
import com.example.hotelmanagementsystem.R
import com.example.hotelmanagementsystem.database.NotesDB.NotesEntity
import com.example.hotelmanagementsystem.databinding.FragmentNotesAddBinding
import com.example.hotelmanagementsystem.databinding.FragmentNotesDetailsBinding
import com.example.hotelmanagementsystem.notes.viewmodel.NotesDatabaseViewModel
import com.example.hotelmanagementsystem.notes.viewmodel.NotesViewModel

class NotesAddFragment : Fragment() {

    private val sharedViewModel: NotesViewModel by activityViewModels()
    private lateinit var notesDatabaseViewModel: NotesDatabaseViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentNotesAddBinding>(inflater,
                R.layout.fragment_notes_add, container,false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Add Notes"

        // Initiate viewmodel
        notesDatabaseViewModel = ViewModelProvider(this).get(NotesDatabaseViewModel::class.java)

        // Save button
        binding.notesAddSaveBtn.setOnClickListener{
            saveNotesOnclick(it, binding)
        }


        return binding.root
    }

    private fun saveNotesOnclick(view: View, binding : FragmentNotesAddBinding){

        // Variable declaration
        var notesID:String = binding.notesAddID.text.toString()
        var notesTitle:String = binding.notesAddTitle.text.toString()
        var notesDesc:String = binding.notesAddDesc.text.toString()


        if(notesTitle.toString() == ""){
            DrawableCompat.setTint(binding.notesAddTitle.background, Color.RED)
            binding.notesAddDesc.setError("This field is required!")
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

        findNavController().navigate(R.id.action_notesAddFragment_to_notesFragment)
    }

}