package com.example.noteapp.ui

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.noteapp.data.Note
import com.example.noteapp.databinding.FragmentNoteDetailBinding
import com.example.noteapp.utils.OnNoteChangeClickListener
import com.example.noteapp.utils.OnNoteDeleteClickListener

class NoteDetailFragment : Fragment() {

    companion object {
        private const val ARG_NOTE = "arg_note"

        fun newInstance(note: Note): NoteDetailFragment {
            val fragment = NoteDetailFragment()
            val args = Bundle()
            args.putParcelable(ARG_NOTE, note)
            fragment.arguments = args
            return fragment
        }
    }

    // View Binding and data
    private var binding: FragmentNoteDetailBinding? = null
    private var note: Note? = null
    private var noteChangeClickListener: OnNoteChangeClickListener? = null
    private var noteDeleteClickListener: OnNoteDeleteClickListener? = null
    private var newNoteDescription = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            note = it.getParcelable(ARG_NOTE)!!
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Check if listeners are implemented in the hosting Activity
        if (context is OnNoteChangeClickListener) {
            noteChangeClickListener = context
        } else {
            throw RuntimeException("$context must implement OnNoteChangeListener")
        }

        if (context is OnNoteDeleteClickListener) {
            noteDeleteClickListener = context
        } else {
            throw RuntimeException("$context must implement OnNoteDeleteClickListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up UI elements
        binding?.titleNote?.text = note?.title

        binding?.descriptionNote?.apply {
            setText(note?.description)
            addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?, start: Int, count: Int, after: Int
                ) {
                    // Not used in this case
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    newNoteDescription = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {
                    // Not used in this case
                }
            })
        }

        // Set click listeners for buttons
        binding?.saveBtn?.setOnClickListener {
            noteChangeClickListener?.onNoteChange(note?.id ?: -1, newNoteDescription)
        }

        binding?.deleteBtn?.setOnClickListener {
            noteDeleteClickListener?.onNoteDelete(note?.id ?: -1)
        }

        binding?.backBtn?.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    override fun onDetach() {
        // Clear listeners to avoid leaks
        noteChangeClickListener = null
        noteDeleteClickListener = null
        super.onDetach()
    }

    override fun onDestroy() {
        // Nullifying references
        note = null
        super.onDestroy()
    }

    override fun onDestroyView() {
        // Nullifying references
        binding = null
        super.onDestroyView()
    }
}