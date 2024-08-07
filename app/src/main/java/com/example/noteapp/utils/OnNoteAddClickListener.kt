package com.example.noteapp.utils

import com.example.noteapp.data.Note

interface OnNoteAddClickListener {
    fun onNoteAdd(newNote: Note)
}