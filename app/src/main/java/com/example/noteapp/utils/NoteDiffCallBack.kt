package com.example.noteapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.noteapp.data.Note

class NoteDiffCallBack(
    private val oldNoteList: List<Note>, private val newNoteList: List<Note>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldNoteList.size

    override fun getNewListSize(): Int = newNoteList.size

    override fun areItemsTheSame(oldNotePosition: Int, newNotePosition: Int): Boolean =
        oldNoteList[oldNotePosition].id == newNoteList[newNotePosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldNoteList[oldItemPosition] == newNoteList[newItemPosition]
}