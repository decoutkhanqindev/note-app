package com.example.noteapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.databinding.ActivityAllNotesBinding
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel

class AllNotesActivity : AppCompatActivity() {
    // View Binding
    private val binding: ActivityAllNotesBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityAllNotesBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<NoteViewModel>(factoryProducer = {
        viewModelFactory {
            addInitializer(clazz = NoteViewModel::class) {
                NoteViewModel(application = application)
            }
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.getAllNotesService()
        viewModel.notesLiveData.observe(this) { notes ->
            createRecycleView(notes)
        }
    }

    private fun createRecycleView(notes: List<Note>) {
        binding.recycleView.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = NoteAdapter(notes)
        }
    }
}