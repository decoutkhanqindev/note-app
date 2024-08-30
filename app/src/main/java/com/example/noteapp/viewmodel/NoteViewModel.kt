package com.example.noteapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import com.example.noteapp.apiservice.NoteService
import com.example.noteapp.apiservice.NoteServiceState
import com.example.noteapp.apiservice.ServiceLocator
import com.example.noteapp.database.DatabaseLocator
import com.example.noteapp.database.NoteDatabase
import com.example.noteapp.model.Note
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    // api
    private val noteService: NoteService = ServiceLocator.noteService
    private val noteServiceMutableLiveData: MutableLiveData<NoteServiceState> =
        MutableLiveData<NoteServiceState>()
    private val noteServiceLiveData: LiveData<NoteServiceState> get() = noteServiceMutableLiveData

    // db
    private val noteDatabase: NoteDatabase = DatabaseLocator.getInstance(context = application)
    val notesLiveData: LiveData<List<Note>> = noteDatabase.noteDAO().observeAllNotes()

    // get all notes from api
    fun getAllNotesService() {
        noteServiceMutableLiveData.value = NoteServiceState.Loading
        viewModelScope.launch {
            try {
                val response: List<Note> = withContext(Dispatchers.IO) {
                    noteService.getAllNotes()
                }
                noteServiceMutableLiveData.value = NoteServiceState.Success(response)
                noteDatabase.noteDAO().insertNotes(response) // insert notes to db
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



