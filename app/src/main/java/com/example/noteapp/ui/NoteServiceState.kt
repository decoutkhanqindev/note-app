package com.example.noteapp.ui

import com.example.noteapp.model.Note

sealed interface NoteServiceState {
    data object Loading : NoteServiceState
    data class Success(val notes: List<Note>) : NoteServiceState
    data class Error(val throwable: Throwable) : NoteServiceState
}