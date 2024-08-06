package com.example.noteapp.utils

interface OnNoteChangeClickListener {
    fun onNoteChange(noteId: Int, newNoteDescription: String)
}