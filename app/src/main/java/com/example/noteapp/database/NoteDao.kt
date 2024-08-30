package com.example.noteapp.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.noteapp.model.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    fun observeAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM notes")
    fun getAllNotes(): List<Note>

    @Insert
    fun insertNote(note: Note)

    @Insert
    fun insertNotes(notes: List<Note>)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)
}