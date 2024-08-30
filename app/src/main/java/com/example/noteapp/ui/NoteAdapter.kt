package com.example.noteapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.databinding.NoteItemLayoutBinding
import com.example.noteapp.model.Note

class NoteAdapter(
    private val onNoteCLick: (Note) -> Unit
) : ListAdapter<Note, NoteAdapter.NoteViewHolder>(NoteDiffUtilItemCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
        NoteViewHolder(
            NoteItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) =
        holder.bind(note = getItem(position))

    inner class NoteViewHolder(private val binding: NoteItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position: Int = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onNoteCLick(getItem(position))
                }
            }
        }

        fun bind(note: Note) {
            binding.run {
                titleNote.text = note.title
                descriptionNote.text = note.description
            }
        }
    }

    private object NoteDiffUtilItemCallBack : DiffUtil.ItemCallback<Note>() {
        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean =
            oldItem.id == newItem.id

        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean = oldItem == newItem
    }
}

//class NoteAdapter(private val notes: List<Note>) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
//        NoteViewHolder(
//            NoteItemLayoutBinding.inflate(
//                LayoutInflater.from(parent.context), parent, false
//            )
//        )
//
//    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) =
//        holder.bind(note = notes[position])
//
//    override fun getItemCount(): Int = notes.size
//
//    class NoteViewHolder(
//        private val binding: NoteItemLayoutBinding
//    ) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(note: Note) {
//            binding.run {
//                titleNote.text = note.title
//                descriptionNote.text = note.description
//            }
//        }
//    }
//}