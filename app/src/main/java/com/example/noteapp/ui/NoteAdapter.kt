package com.example.noteapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.databinding.NoteItemLayoutBinding
import com.example.noteapp.data.Note

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private var notes = emptyList<Note>()

    fun updateNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
        NoteViewHolder(
            NoteItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) =
        holder.bind(notes[position])

    override fun getItemCount(): Int = notes.size

    class NoteViewHolder(private val binding: NoteItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.run {
                titleNote.text = note.title
                titleImg.setImageResource(note.img)
                descriptionNote.text = note.description
            }
        }
    }
}