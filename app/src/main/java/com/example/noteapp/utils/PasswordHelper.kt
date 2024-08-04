package com.example.noteapp.utils

import android.text.InputType
import android.widget.EditText
import android.widget.ImageView
import com.example.noteapp.R

class PasswordHelper {
//    before click isVisiblePassword = false
//    first click isVisiblePassword = true
//    second click isVisiblePassword = false
//    next click isVisiblePassword = true
//    ....

    fun setUpPasswordVisibilityBtn(button: ImageView, editText: EditText) {
        var isVisiblePassword = false
        button.setOnClickListener {
            isVisiblePassword = !isVisiblePassword
            setUpPasswordVisibilityText(button, editText, isVisiblePassword)
        }
    }

    private fun setUpPasswordVisibilityText(
        button: ImageView, editText: EditText, isVisiblePassword: Boolean
    ) {
        val imageResource = if (isVisiblePassword) {
            R.drawable.visible_pwd
        } else {
            R.drawable.invisible_pwd
        }
        button.setImageResource(imageResource)

        editText.inputType = if (isVisiblePassword) {
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        } else {
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
    }
}