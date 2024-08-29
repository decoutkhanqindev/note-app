package com.example.noteapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.noteapp.databinding.ActivityAllNotesBinding

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