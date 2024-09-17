package com.example.noteapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.AppLocator
import com.example.noteapp.R
import com.example.noteapp.databinding.ActivityAllNotesBinding
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel

class AllNotesActivity : AppCompatActivity() {
  private val binding: ActivityAllNotesBinding by lazy(LazyThreadSafetyMode.NONE) {
    ActivityAllNotesBinding.inflate(layoutInflater)
  }
  
  private val viewModel: NoteViewModel by viewModels<NoteViewModel>(factoryProducer = {
    viewModelFactory {
      addInitializer(clazz = NoteViewModel::class) {
        NoteViewModel(
          application = application,
          noteService = AppLocator.noteService,
          noteDatabase = AppLocator.getDBInstance(application)
        )
      }
    }
  })
  
  private val noteAdapter: NoteAdapter by lazy(LazyThreadSafetyMode.NONE) {
    NoteAdapter(onNoteCLick = ::onNoteClick)
  }
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    
    viewModel.getAllNotesService()
    
    viewModel.notesLiveData.observe(this) { notes: List<Note> ->
      noteAdapter.submitList(notes)
      if (notes.isEmpty()) {
        binding.emptyRecycleView.setImageResource(R.drawable.undraw_empty_xct9_1)
      }
    }
    
    initRecycleView()
    
    binding.addBtn.setOnClickListener { moveToAddNoteFragment() }
    
    supportFragmentManager.addOnBackStackChangedListener { handleBackStackToUi() }
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
        arguments = Bundle().apply { putParcelable("note", note) }
      })
      addToBackStack(null)
    }
  }
  
  private fun moveToAddNoteFragment() {
    supportFragmentManager.commit {
      setReorderingAllowed(true)
      replace(binding.fragmentContainerView.id, AddNoteFragment())
      addToBackStack(null)
    }
  }
  
  private fun handleBackStackToUi() {
    if (supportFragmentManager.backStackEntryCount == 0) {
      binding.addBtn.isVisible = true
      binding.recycleView.isVisible = true
      binding.emptyRecycleView.isVisible = true
    } else {
      binding.addBtn.isVisible = false
      binding.recycleView.isVisible = false
      binding.emptyRecycleView.isVisible = false
    }
  }
}
