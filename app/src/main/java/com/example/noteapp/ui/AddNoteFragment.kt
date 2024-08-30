package com.example.noteapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.noteapp.databinding.FragmentAddNoteBinding
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel

class AddNoteFragment : Fragment() {
    private var _binding: FragmentAddNoteBinding? = null
    private val binding: FragmentAddNoteBinding get() = _binding!!

    private val viewModel: NoteViewModel by viewModels<NoteViewModel>(factoryProducer = {
        viewModelFactory {
            addInitializer(NoteViewModel::class) {
                NoteViewModel(requireActivity().application)
            }
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveBtn.setOnClickListener {
            if (binding.editTitleNote.text.toString().isNotEmpty()) {
                viewModel.insertNote(
                    Note(
                        id = System.currentTimeMillis().toInt(),
                        title = binding.editTitleNote.text.toString(),
                        description = binding.editDescriptionNote.text.toString()
                    )
                )
            }
        }

        binding.backBtn.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}