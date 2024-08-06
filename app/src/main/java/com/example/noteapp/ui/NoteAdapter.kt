package com.example.noteapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.data.Note
import com.example.noteapp.databinding.NoteItemLayoutBinding
import com.example.noteapp.ui.NoteAdapter.NoteViewHolder

class NoteAdapter : RecyclerView.Adapter<NoteViewHolder>() {
    private var notes = emptyList<Note>()
    private var onNoteClickListener: NoteClickListener? = null

    fun updateNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    fun setOnNoteClickListener(onNoteClickListener: NoteClickListener?) {
        this.onNoteClickListener = onNoteClickListener
    }

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

    interface NoteClickListener {
        fun onNoteClick(note: Note)
    }

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

//// new style
//object NoteDiffCallBack : DiffUtil.ItemCallback<Note>() {
//    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean =
//        oldItem.id == newItem.id
//
//    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean = oldItem == newItem
//}
//
//class NoteListAdapter(
////    private val onItemClick: (Note) -> Unit
//) : ListAdapter<Note, NoteListAdapter.NoteViewHolder>(NoteDiffCallBack) { // tinh toan dc su khac biet giua list cu va list moi
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
//        NoteViewHolder(
//            NoteItemLayoutBinding.inflate(
//                LayoutInflater.from(parent.context), parent, false
//            )
//        )
//
//    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) =
//        holder.bind(getItem(position))
//
//    inner class NoteViewHolder(private val binding: NoteItemLayoutBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        init {
//            binding.root.setOnClickListener {
//                if (adapterPosition != RecyclerView.NO_POSITION) {
////                    onItemClick(getItem(adapterPosition))
//                    getItem(adapterPosition)
//                }
//            }
//        }
//
//        fun bind(note: Note) {
//            binding.run {
//                titleNote.text = note.title
//                descriptionNote.text = note.description
//            }
//        }
//    }
//}