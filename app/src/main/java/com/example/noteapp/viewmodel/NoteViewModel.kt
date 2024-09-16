package com.example.noteapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.noteapp.apiservice.NoteService
import com.example.noteapp.ui.NoteServiceState
import com.example.noteapp.database.NoteDatabase
import com.example.noteapp.model.Note
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteViewModel(
  application: Application,
  private val noteService: NoteService,
  private val noteDatabase: NoteDatabase
) : AndroidViewModel(application) {
  // api
  private val noteServiceMutableLiveData: MutableLiveData<NoteServiceState> =
    MutableLiveData<NoteServiceState>()
  private val noteServiceLiveData: LiveData<NoteServiceState> get() = noteServiceMutableLiveData
  
  // db
  val notesLiveData: LiveData<List<Note>> = noteDatabase.noteDAO().observeAllNotes()
  
  init {
    getAllNotesService()
  }
  
  // get all notes from api
  private fun getAllNotesService() {
    noteServiceMutableLiveData.value = NoteServiceState.Loading
    viewModelScope.launch {
      try {
        val notes: List<Note> = withContext(Dispatchers.IO) {
          noteService.getAllNotes()
        }
        noteServiceMutableLiveData.value = NoteServiceState.Success(notes)
        if (noteDatabase.noteDAO().observeAllNotes().value == null) {
          noteDatabase.noteDAO().insertNotes(notes) // insert notes to db
        } else {
          noteDatabase.noteDAO().updateNotes(notes)
        }
      } catch (cancel: CancellationException) {
        throw cancel
      } catch (throwable: Throwable) {
        noteServiceMutableLiveData.value = NoteServiceState.Error(throwable)
      }
      Log.d("NoteViewModel", "getAllNotesService: ${noteServiceLiveData.value}")
    }
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



