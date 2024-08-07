package com.example.noteapp.utils

object GenerateUniqueId {
    fun generateUniqueId(): Int {
        return System.currentTimeMillis().toInt()
    }
}