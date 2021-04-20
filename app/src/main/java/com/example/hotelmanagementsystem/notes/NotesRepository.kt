package com.example.hotelmanagementsystem.notes

import androidx.lifecycle.LiveData
import com.example.hotelmanagementsystem.database.notesDB.NotesDao
import com.example.hotelmanagementsystem.database.notesDB.NotesEntity

class NotesRepository(private val notesDao: NotesDao) {

    val readAllData: LiveData<List<NotesEntity>> = notesDao.getAllNotes()

    suspend fun addNotes(notes: NotesEntity){
        notesDao.insertNotes(notes)
    }

    suspend fun updateNotes(notes: NotesEntity){
        notesDao.updateNotes(notes)
    }

    suspend fun updateNotesDetails(notesDesc: String, notesID:Int){
        notesDao.updateNotesDetails(notesDesc, notesID)
    }

    suspend fun deleteNotes(notes: NotesEntity){
        notesDao.deleteNotes(notes)
    }

    suspend fun deleteAllNotes(){
        notesDao.deleteAllNotes()
    }
}