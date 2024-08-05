package com.example.noteapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.data.Note
import com.example.noteapp.databinding.ActivityAllNotesBinding

class AllNotesActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityAllNotesBinding.inflate(layoutInflater)
    }

    private val noteAdapter by lazy(LazyThreadSafetyMode.NONE) {
        NoteAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recycleView.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = noteAdapter
        }

        noteAdapter.updateNotes(
            listOf(
                Note(
                    title = "Youtube script ideas \uD83C\uDF96",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                ), Note(
                    title = "Datastore Blog Ideas  \uD83E\uDD8B",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                ), Note(
                    title = "College skit review \uD83C\uDFC4\uD83D\uDE1D",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                ), Note(
                    title = "Social media blogs  \uD83D\uDCD5",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                ), Note(
                    title = "Youtube script ideas \uD83C\uDF96",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                ), Note(
                    title = "Datastore Blog Ideas  \uD83E\uDD8B",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                ), Note(
                    title = "College skit review \uD83C\uDFC4\uD83D\uDE1D",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                ), Note(
                    title = "Social media blogs  \uD83D\uDCD5",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                ), Note(
                    title = "Youtube script ideas \uD83C\uDF96",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                ), Note(
                    title = "Datastore Blog Ideas  \uD83E\uDD8B",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                ), Note(
                    title = "College skit review \uD83C\uDFC4\uD83D\uDE1D",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                ), Note(
                    title = "Social media blogs  \uD83D\uDCD5",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                ), Note(
                    title = "Youtube script ideas \uD83C\uDF96",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                ), Note(
                    title = "Datastore Blog Ideas  \uD83E\uDD8B",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                ), Note(
                    title = "College skit review \uD83C\uDFC4\uD83D\uDE1D",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                ), Note(
                    title = "Social media blogs  \uD83D\uDCD5",
                    description = "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.\n" + "\n" + "Screen readers, speech-to-text and some of the newest ways to experience the world your way.\n" + "\n" + "Choices for work, gaming, 5G streaming and anything else. There are over 24,000 phones and tablets that run on Android globally. So no matter what you’re looking for, there’s something for you."
                )
            )
        )
    }
}