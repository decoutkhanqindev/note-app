package com.example.noteapp.data

import android.os.Parcel
import android.os.Parcelable

data class Note(
    val id: Int, val title: String, val description: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(), parcel.readString() ?: "", parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
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