package com.example.noteapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.data.Note
import com.example.noteapp.databinding.NoteItemLayoutBinding
import com.example.noteapp.ui.NoteAdapter.NoteViewHolder
import com.example.noteapp.utils.NoteDiffCallBack
import com.example.noteapp.utils.OnNoteChangeClickListener
import com.example.noteapp.utils.OnNoteClickListener
import com.example.noteapp.utils.OnNoteDeleteClickListener

class NoteAdapter : RecyclerView.Adapter<NoteViewHolder>() {

    // Listener interfaces
    fun setOnNoteClickListener(onNoteClickListener: OnNoteClickListener?) {
        this.onNoteClickListener = onNoteClickListener
    }

    fun setOnNoteChangeClickListener(onNoteChangeClickListener: OnNoteChangeClickListener) {
        this.onNoteChangeClickListener = onNoteChangeClickListener
    }

    fun setOnNoteDeleteClickListener(onNoteDeleteClickListener: OnNoteDeleteClickListener) {
        this.onNoteDeleteClickListener = onNoteDeleteClickListener
    }

    // Data and listeners
    private var notes = mutableListOf<Note>()
    private var onNoteClickListener: OnNoteClickListener? = null
    private var onNoteChangeClickListener: OnNoteChangeClickListener? = null
    private var onNoteDeleteClickListener: OnNoteDeleteClickListener? = null

    // Update notes in the adapter
    fun updateNotes(notes: List<Note>) {
        val noteDiffCallBack = NoteDiffCallBack(this.notes, notes)
        val noteDiffResult = DiffUtil.calculateDiff(noteDiffCallBack)
        this.notes.clear()
        this.notes.addAll(notes)
        noteDiffResult.dispatchUpdatesTo(this)
    }

    // Change note description
    fun changeNoteDescription(noteId: Int, newNoteDescription: String) {
        val note = notes.find { it.id == noteId }
        note?.let {
            it.description = newNoteDescription
            notifyItemChanged(notes.indexOf(note))
        }
    }

    // Delete a note
    fun deleteNote(noteId: Int) {
        val note = notes.find { it.id == noteId }
        notes.remove(note)
        notifyItemRemoved(notes.indexOf(note))
    }

    // RecyclerView.Adapter methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
        NoteViewHolder(
            NoteItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])

        holder.itemView.setOnClickListener {
            onNoteClickListener?.onNoteClick(notes[position])
        }
    }

    override fun getItemCount(): Int = notes.size

    // ViewHolder class
    class NoteViewHolder(private val binding: NoteItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.run {
                titleNote.text = note.title
                descriptionNote.text = note.description
            }
        }
    }
}

