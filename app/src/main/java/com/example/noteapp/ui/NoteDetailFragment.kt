package com.example.noteapp.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.noteapp.databinding.FragmentNoteDetailBinding
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel

class NoteDetailFragment : Fragment() {
    private var _binding: FragmentNoteDetailBinding? = null
    private val binding: FragmentNoteDetailBinding get() = _binding!!

    private var note: Note? = null

    private val viewModel by viewModels<NoteViewModel>(factoryProducer = {
        viewModelFactory {
            addInitializer(NoteViewModel::class) {
                NoteViewModel(application = requireActivity().application)
            }
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        note = arguments?.getSerializable("note") as? Note
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.titleNote.text = note?.title.toString()

        binding.descriptionNote.apply {
            setText(note?.description)
            addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?, start: Int, count: Int, after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    note?.description = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })

            binding.saveBtn.setOnClickListener {
                viewModel.updateNote(note!!)
            }

            binding.backBtn.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}