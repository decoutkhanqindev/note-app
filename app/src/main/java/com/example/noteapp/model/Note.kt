package com.example.noteapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "notes")
data class Note(
    @Json(name = "id") @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Int?,
    @Json(name = "title") @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "description") @Json(name = "description") val description: String?
)
