package com.example.noteapp.utils

object GenerateUniqueId {
    fun generateUniqueId(): Int = System.currentTimeMillis().toInt()
}