package com.example.hotelmanagementsystem.database.NotesDB

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize // To make this object able to pass to another fragment

@Entity(tableName = "notes_table")
data class  NotesEntity(

    @PrimaryKey(autoGenerate = true)
    val notesId: Int,

    @ColumnInfo(name = "notes_title")
    val notesTitle: String,

    @ColumnInfo(name = "notes_details")
    val notesDetails: String

): Parcelable
