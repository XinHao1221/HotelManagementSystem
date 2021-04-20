package com.example.hotelmanagementsystem.notes.viewmodel

import androidx.lifecycle.ViewModel


class NotesViewModel(): ViewModel() {

    // Neccessary data for Notes
    // notes_title setter and getter

    private var _notesID = ""
    val notesID:String
        get() = _notesID

    private var _notesTitle = ""
    val notesTitle: String
        get() = _notesTitle

    private var _notesDesc = ""
    val notesDesc: String
        get() = _notesDesc

    // Setter for each variable

    fun setNotesID(notesID: String) {
        _notesID = notesID
    }

    fun setNotesTitle(notesTitle: String) {
        _notesTitle = notesTitle
    }

    fun setNotesDesc(notesDesc: String) {
        _notesDesc = notesDesc
    }

}