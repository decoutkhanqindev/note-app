package com.example.noteapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(tableName = "notes")
data class Note(
    @Json(name = "id") @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Int?,
    @Json(name = "title") @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "description") @Json(name = "description") var description: String?
) : Serializable