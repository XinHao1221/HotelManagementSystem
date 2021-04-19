package com.example.hotelmanagementsystem.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hotelmanagementsystem.R
import com.example.hotelmanagementsystem.databinding.FragmentNotesDetailsBinding
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

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Notes Details"

        // Initiate viewmodel
        notesDatabaseViewModel = ViewModelProvider(this).get(NotesDatabaseViewModel::class.java)

        // Set all value inside args into sharedViewModel
        setArgsValueToSharedViewModel()

        // Display all value in sharedViewModel
        displayNotesDetails(binding)

        // Save button
        binding.notesEditBtn.setOnClickListener{
            findNavController().navigate(R.id.action_reservationDetailFragment_to_updateReservationFragment1)
        }

        return binding.root
    }

    fun setArgsValueToSharedViewModel(){
        sharedViewModel.setNotesID(args.notes.notesId.toString())
        sharedViewModel.setNotesTitle(args.notes.notesTitle)
        sharedViewModel.setNotesDesc(args.notes.notesDetails)

    }

    fun displayNotesDetails(binding: FragmentNotesDetailsBinding){
        binding.notesDetailsID.setText(sharedViewModel.notesID)
        binding.notesDetailsTitle.setText(sharedViewModel.notesTitle)

        if(sharedViewModel.notesDesc == "")
            binding.notesDetailsDesc.setText("No description.")
        else
            binding.notesDetailsDesc.setText(sharedViewModel.notesDesc)
    }

//    private fun saveNotesOnclick(view: View, binding : FragmentNotesDetailsBinding){
//
//        // Variable declaration
//        var notesID:String = binding.notesDetailsID.text.toString()
//        var notesTitle:String = binding.notesDetailsTitle.text.toString()
//        var notesDesc:String = binding.notesDetailsDesc.text.toString()
//
//
//        if(notesTitle.toString() == ""){
//            DrawableCompat.setTint(binding.notesDetailsTitle.background, Color.RED)
//            binding.notesDetailsDesc.setError("This field is required!")
//        }
//        else{
//
//            val builder = AlertDialog.Builder(requireContext())
//
//            builder.setNegativeButton("No"){_, _ ->
//
//            }
//            // Confirm save
//            builder.setPositiveButton("Yes"){_, _->
//                sharedViewModel.setNotesID(notesID)
//                sharedViewModel.setNotesTitle(notesTitle)
//                sharedViewModel.setNotesDesc(notesDesc)
//
//                insertDataToDatabase()
//            }
//
//            builder.setTitle("Save?");
//
//            builder.setMessage("Confirm Save?")
//            builder.create().show()
//        }
//
//
//    }
//
//    private fun insertDataToDatabase(){
//
//        var notesTitle = sharedViewModel.notesTitle.toString()
//        var notesDesc = sharedViewModel.notesTitle.toString()
//
//        // Create Notes object
//        val notes = NotesEntity(0, notesTitle, notesDesc)
//
//        notesDatabaseViewModel.addNotes(notes)
//
//        Toast.makeText(requireContext(), "Notes Saved", Toast.LENGTH_LONG).show()
//    }

}