package com.example.noteapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.data.Note
import com.example.noteapp.databinding.ActivityAllNotesBinding
import com.example.noteapp.utils.OnNoteChangeClickListener
import com.example.noteapp.utils.OnNoteClickListener
import com.example.noteapp.utils.OnNoteDeleteClickListener
import kotlin.random.Random

class AllNotesActivity : AppCompatActivity(), OnNoteChangeClickListener, OnNoteDeleteClickListener {

    // View Binding
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityAllNotesBinding.inflate(layoutInflater)
    }

    // Adapter for RecyclerView
    private val noteAdapter by lazy(LazyThreadSafetyMode.NONE) {
        NoteAdapter()
    }

    // Visibility properties for UI elements
    private var isAddBtnVisible
        get() = binding.addBtn.visibility == View.VISIBLE
        set(value) {
            binding.addBtn.visibility = if (value) View.VISIBLE else View.GONE
        }

    private var isRecycleViewVisible
        get() = binding.recycleView.visibility == View.VISIBLE
        set(value) {
            binding.recycleView.visibility = if (value) View.VISIBLE else View.GONE
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // RecyclerView setup
        binding.recycleView.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = noteAdapter
        }

        // Set listeners for Adapter
        noteAdapter.setOnNoteChangeClickListener(this)
        noteAdapter.setOnNoteDeleteClickListener(this)
        noteAdapter.setOnNoteClickListener(object : OnNoteClickListener {
            override fun onNoteClick(note: Note) {
                // Fragment transaction when a note is clicked
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace(binding.fragmentContainerView.id, NoteDetailFragment.newInstance(note))
                    addToBackStack(null)
                }
                // Update UI visibility
                isAddBtnVisible = false
                isRecycleViewVisible = false
            }
        })

        // Handle back stack changes to update UI visibility
        supportFragmentManager.addOnBackStackChangedListener {
            isAddBtnVisible = supportFragmentManager.backStackEntryCount == 0
            isRecycleViewVisible = supportFragmentManager.backStackEntryCount == 0
        }

        // Initialize notes data
        noteAdapter.updateNotes(
            mutableListOf(
                Note(
                    id = Random.nextInt(1, 30),
                    title = "Youtube script ideas \uD83C\uDF96",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                ), Note(
                    id = Random.nextInt(1, 30),
                    title = "Datastore Blog Ideas  \uD83E\uDD8B",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                ), Note(
                    id = Random.nextInt(1, 30),
                    title = "College skit review \uD83C\uDFC4\uD83D\uDE1D",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                ), Note(
                    id = Random.nextInt(1, 30),
                    title = "Social media blogs  \uD83D\uDCD5",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                ), Note(
                    id = Random.nextInt(1, 30),
                    title = "Youtube script ideas \uD83C\uDF96",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                ), Note(
                    id = Random.nextInt(1, 30),
                    title = "Datastore Blog Ideas  \uD83E\uDD8B",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                ), Note(
                    id = Random.nextInt(1, 30),
                    title = "College skit review \uD83C\uDFC4\uD83D\uDE1D",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                ), Note(
                    id = Random.nextInt(1, 30),
                    title = "Social media blogs  \uD83D\uDCD5",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                ), Note(
                    id = Random.nextInt(1, 30),
                    title = "Youtube script ideas \uD83C\uDF96",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                ), Note(
                    id = Random.nextInt(1, 30),
                    title = "Datastore Blog Ideas  \uD83E\uDD8B",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                ), Note(
                    id = Random.nextInt(1, 30),
                    title = "College skit review \uD83C\uDFC4\uD83D\uDE1D",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                ), Note(
                    id = Random.nextInt(1, 30),
                    title = "Social media blogs  \uD83D\uDCD5",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                ), Note(
                    id = Random.nextInt(1, 30),
                    title = "Youtube script ideas \uD83C\uDF96",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                ), Note(
                    id = Random.nextInt(1, 30),
                    title = "Datastore Blog Ideas  \uD83E\uDD8B",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                ), Note(
                    id = Random.nextInt(1, 30),
                    title = "College skit review \uD83C\uDFC4\uD83D\uDE1D",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                ), Note(
                    id = Random.nextInt(1, 30),
                    title = "Social media blogs  \uD83D\uDCD5",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                )
            )
        )
    }

    // Listener implementations
    override fun onNoteChange(noteId: Int, newNoteDescription: String) {
        noteAdapter.changeNoteDescription(noteId, newNoteDescription)
    }

    override fun onNoteDelete(noteId: Int) {
        noteAdapter.deleteNote(noteId)
    }
}