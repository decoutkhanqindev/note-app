package com.example.noteapp.apiservice

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ServiceLocator {
    private const val BASE_URL: String =
        "https://44cda99f-cdc5-4940-9280-602add0ce168.mock.pstmn.io"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshiProvider())).build()
    }

    private fun moshiProvider(): Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    val noteService: NoteService by lazy {
        retrofit.create(NoteService::class.java)
    }
}