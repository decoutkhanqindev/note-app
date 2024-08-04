package com.example.noteapp

import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.commit
import com.example.noteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var isVisiblePassword = false

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.signUpBtn.setOnClickListener {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(binding.fragmentContainerView.id, SignUpFragment())
                addToBackStack(null)
            }
        }

        setUpPasswordVisibilityBtn()
    }

    private fun setUpPasswordVisibilityBtn() {
        // before click isVisiblePassword = false
        // first click isVisiblePassword = true
        // second click isVisiblePassword = false
        // next click isVisiblePassword = true
        // ....
        binding.visibilityPasswordBtn.setOnClickListener {
            isVisiblePassword = !isVisiblePassword
//            Log.d("isVisiblePassword", isVisiblePassword.toString())
            setUpPasswordVisibilityText()
        }
    }

    private fun setUpPasswordVisibilityText() {
        val imageResource = if (isVisiblePassword) {
            R.drawable.visible_pwd
        } else {
            R.drawable.invisible_pwd
        }
        binding.visibilityPasswordBtn.setImageResource(imageResource)

        binding.editPassword.inputType = if (isVisiblePassword) {
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        } else {
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
    }
}