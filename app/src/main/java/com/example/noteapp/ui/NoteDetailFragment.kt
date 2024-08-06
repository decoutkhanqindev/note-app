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

class NoteDetailFragment : Fragment() {
    private var binding: FragmentNoteDetailBinding? = null
    private var note: Note? = null
    private var listener: OnNoteChangeClickListener? = null
    private var newNoteDescription = ""

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            note = it.getParcelable(ARG_NOTE)!!
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnNoteChangeClickListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnNoteChangeListener")
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

        binding?.titleNote?.text = note?.title

        binding?.descriptionNote?.apply {
            setText(note?.description)
            addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?, start: Int, count: Int, after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    newNoteDescription = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }

        binding?.saveBtn?.setOnClickListener {
            listener?.onNoteChange(note?.id ?: -1, newNoteDescription)
        }

        binding?.backBtn?.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    override fun onDestroy() {
        note = null
        super.onDestroy()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}