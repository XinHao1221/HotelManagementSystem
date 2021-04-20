package com.example.hotelmanagementsystem.database.notesDB

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {

    // Reservation table
    // Insert new reservation record
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNotes(notes: NotesEntity)

    @Update
    suspend fun updateNotes(notes: NotesEntity)

    @Delete
    suspend fun  deleteNotes(notes: NotesEntity)

    @Query("SELECT * FROM notes_table ORDER BY notesId ASC")
    fun getAllNotes(): LiveData<List<NotesEntity>>

    @Query("SELECT * FROM notes_table WHERE notesId = :key")
    suspend fun getNotes(key: Long): NotesEntity?

    @Query("UPDATE notes_table SET notes_details = :notes_details WHERE notesId = :notesID")
    fun updateNotesDetails(notes_details: String, notesID : Int)

    @Query("DELETE FROM notes_table")
    suspend fun deleteAllNotes()




}