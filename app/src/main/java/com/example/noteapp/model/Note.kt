package com.example.noteapp.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(tableName = "notes")
data class Note(
  @Json(name = "id")
  @ColumnInfo(name = "id")
  @PrimaryKey(autoGenerate = true)
  val id: Int?,
  
  @Json(name = "title")
  @ColumnInfo(name = "title")
  val title: String?,
  
  @ColumnInfo(name = "description")
  @Json(name = "description")
  var description: String?
) : Parcelable, Serializable {
  
  constructor(parcel: Parcel) : this(
    parcel.readValue(Int::class.java.classLoader) as? Int, parcel.readString(), parcel.readString()
  )
  
  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeValue(id)
    parcel.writeString(title)
    parcel.writeString(description)
  }
  
  override fun describeContents(): Int {
    return 0
  }
  
  companion object CREATOR : Parcelable.Creator<Note> {
    override fun createFromParcel(parcel: Parcel): Note {
      return Note(parcel)
    }
    
    override fun newArray(size: Int): Array<Note?> {
      return arrayOfNulls(size)
    }
  }
}