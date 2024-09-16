package com.example.noteapp

import android.content.Context
import androidx.room.Room
import com.example.noteapp.apiservice.NoteService
import com.example.noteapp.database.NoteDatabase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object AppLocator {
    // api
    private const val BASE_URL: String =
        "https://44cda99f-cdc5-4940-9280-602add0ce168.mock.pstmn.io"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshiProvider())).build()
    }

    private fun moshiProvider(): Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    val noteService: NoteService by lazy { retrofit.create(NoteService::class.java) }
    
    // database
    private const val DB_NAME: String = "note.db"
    
    @Volatile
    private var INSTANCE: NoteDatabase? = null
    
    private fun databaseProvider(context: Context): NoteDatabase = Room.databaseBuilder(
        context = context.applicationContext, klass = NoteDatabase::class.java, name = DB_NAME
    ).build()
    
    fun getDBInstance(context: Context): NoteDatabase = INSTANCE ?: synchronized(lock = this) {
        INSTANCE ?: databaseProvider(context).also { it: NoteDatabase -> INSTANCE = it }
    }
}