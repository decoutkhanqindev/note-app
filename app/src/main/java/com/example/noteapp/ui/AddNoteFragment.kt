package com.example.noteapp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.noteapp.data.Note
import com.example.noteapp.databinding.FragmentAddNoteBinding
import com.example.noteapp.utils.GenerateUniqueId
import com.example.noteapp.utils.OnNoteAddClickListener

class AddNoteFragment : Fragment() {
    // View Binding and data
    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!
    private var noteAddClickListener: OnNoteAddClickListener? = null
    private var newNote = Note(0, "", "")

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnNoteAddClickListener) {
            noteAddClickListener = context
        } else {
            throw RuntimeException("$context must implement OnNoteAddClickListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveBtn.setOnClickListener {
            newNote.apply {
                id = GenerateUniqueId.generateUniqueId()
                title = binding.editTitleNote.text.toString()
                description = binding.editDescriptionNote.text.toString()
            }
            noteAddClickListener?.onNoteAdd(newNote)
        }

        binding.backBtn.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    override fun onDetach() {
        noteAddClickListener = null
        super.onDetach()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}