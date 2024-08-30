package com.example.noteapp.database

import android.content.Context
import androidx.room.Room

object DatabaseLocator {
    private const val DB_NAME: String = "note.db"

    @Volatile
    private var INSTANCE: NoteDatabase? = null

    private fun databaseProvider(context: Context): NoteDatabase = Room.databaseBuilder(
        context = context.applicationContext, klass = NoteDatabase::class.java, name = DB_NAME
    ).build()

    fun getInstance(context: Context): NoteDatabase = INSTANCE ?: synchronized(lock = this) {
        INSTANCE ?: databaseProvider(context).also { it: NoteDatabase -> INSTANCE = it }
    }
}