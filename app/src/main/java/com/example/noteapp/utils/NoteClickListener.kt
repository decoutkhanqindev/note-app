package com.example.noteapp.utils

import com.example.noteapp.data.Note


interface NoteClickListener {
    fun onNoteClick (note: Note)
}