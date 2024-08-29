package com.example.noteapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.R
import com.example.noteapp.data.Note
import com.example.noteapp.databinding.ActivityAllNotesBinding
import com.example.noteapp.utils.GenerateUniqueId

class AllNotesActivity : AppCompatActivity() {
    // View Binding
    private val binding: ActivityAllNotesBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityAllNotesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}