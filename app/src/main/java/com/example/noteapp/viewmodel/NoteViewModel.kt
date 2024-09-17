package com.example.noteapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.noteapp.apiservice.NoteService
import com.example.noteapp.database.NoteDatabase
import com.example.noteapp.model.Note
import com.example.noteapp.ui.NoteServiceState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class NoteViewModel(
  application: Application,
  private val noteService: NoteService,
  private val noteDatabase: NoteDatabase
) : AndroidViewModel(application) {
  // api
  private val _apiState: MutableLiveData<NoteServiceState> = MutableLiveData<NoteServiceState>()
  private val apiState: LiveData<NoteServiceState> get() = _apiState
  
  // db
  val notesLiveData: LiveData<List<Note>> = noteDatabase.noteDAO().observeAllNotes()
  
  // get all notes from api
  fun getAllNotesService() {
    viewModelScope.launch {
      _apiState.value = NoteServiceState.Loading
      
      try {
        val notes: List<Note> = noteService.getAllNotes()
        _apiState.value = NoteServiceState.Success(notes)
        
        if (notesLiveData.value.isNullOrEmpty()) {
          noteDatabase.noteDAO().insertNotes(notes)
        }
      } catch (cancel: CancellationException) {
        throw cancel
      } catch (throwable: Throwable) {
        _apiState.value = NoteServiceState.Error(throwable)
      }
    }
    Log.d("NoteViewModel", "getAllNotesService: ${apiState.value}")
  }
  
  fun insertNote(note: Note) {
    viewModelScope.launch {
      try {
        noteDatabase.noteDAO().insertNote(note)
      } catch (cancel: CancellationException) {
        throw cancel
      } catch (throwable: Throwable) {
        Log.d("NoteViewModel", "insertNote: $throwable")
      }
    }
  }
  
  fun updateNote(note: Note) {
    viewModelScope.launch {
      try {
        noteDatabase.noteDAO().updateNote(note)
      } catch (cancel: CancellationException) {
        throw cancel
      } catch (throwable: Throwable) {
        Log.d("NoteViewModel", "updateNote: $throwable")
      }
    }
  }
  
  fun updateNotes(notes: List<Note>) {
    viewModelScope.launch {
      try {
        noteDatabase.noteDAO().updateNotes(notes)
      } catch (cancel: CancellationException) {
        throw cancel
      } catch (throwable: Throwable) {
        Log.d("NoteViewModel", "updateNotes: $throwable")
      }
    }
  }
  
  fun deleteNote(note: Note) {
    viewModelScope.launch {
      try {
        noteDatabase.noteDAO().deleteNote(note)
      } catch (cancel: CancellationException) {
        throw cancel
      } catch (throwable: Throwable) {
        Log.d("NoteViewModel", "deleteNote: $throwable")
      }
    }
  }
}



