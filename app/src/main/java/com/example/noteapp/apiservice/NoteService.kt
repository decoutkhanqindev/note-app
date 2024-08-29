package com.example.noteapp.apiservice

import com.example.noteapp.model.Note
import retrofit2.http.GET

interface NoteService {
    @GET("/note/all")
    suspend fun getAllNotes(): List<Note>
}