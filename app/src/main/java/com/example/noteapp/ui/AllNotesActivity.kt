package com.example.noteapp.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
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

    private val viewModel: NoteViewModel by viewModels<NoteViewModel>(factoryProducer = {
        viewModelFactory {
            addInitializer(clazz = NoteViewModel::class) {
                NoteViewModel(application = application)
            }
        }
    })

    private val noteAdapter: NoteAdapter by lazy(LazyThreadSafetyMode.NONE) {
        NoteAdapter(onNoteCLick = ::onNoteClick)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.notesLiveData.observe(this) { notes: List<Note> ->
            noteAdapter.submitList(notes)
        }
        initRecycleView()
        handleBackStackToUi(view = binding.addBtn)
    }

    private fun initRecycleView() {
        binding.recycleView.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = noteAdapter
        }
    }

    private fun onNoteClick(note: Note) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(binding.fragmentContainerView.id, NoteDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("note", note)
                }
            })
            addToBackStack(null)
        }
    }

    private fun handleBackStackToUi(view: View) {
        supportFragmentManager.addOnBackStackChangedListener {
            view.visibility = if (supportFragmentManager.backStackEntryCount == 0) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }
}
