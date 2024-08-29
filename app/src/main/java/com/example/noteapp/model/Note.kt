package com.example.noteapp.model

import com.squareup.moshi.Json

data class Note(
    @Json(name = "id") val id: String,
	@Json(name = "title") val title: String,
	@Json(name = "description") val description: String
)
