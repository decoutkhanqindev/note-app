package com.example.noteapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noteapp.R
import com.example.noteapp.data.Note
import com.example.noteapp.databinding.FragmentNoteDetailBinding
import com.example.noteapp.utils.OnNoteChangeClickListener
import com.example.noteapp.utils.OnNoteDeleteClickListener

class AddNoteFragment : Fragment() {
    // View Binding and data
    private var binding: FragmentNoteDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}