package com.example.hotelmanagementsystem.notes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hotelmanagementsystem.database.NotesDB.NotesDatabase
import com.example.hotelmanagementsystem.database.NotesDB.NotesEntity
import com.example.hotelmanagementsystem.database.Reservation
import com.example.hotelmanagementsystem.notes.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NotesDatabaseViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<NotesEntity>>


    private val repository: NotesRepository

    init{
        val notesDao = NotesDatabase.getDatabase(application).notesDao()
        repository = NotesRepository(notesDao)
        readAllData = repository.readAllData
    }

    fun addNotes(notes: NotesEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNotes(notes)
        }
    }

    fun deleteNotes(notes: NotesEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNotes(notes)
        }
    }

    fun updateNotesDetails(notes: NotesEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNotes(notes)
        }
    }
}