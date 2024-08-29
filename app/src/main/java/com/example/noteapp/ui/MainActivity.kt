package com.example.noteapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.commit
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.utils.PasswordHelper

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val passwordHelper: PasswordHelper by lazy(LazyThreadSafetyMode.NONE) {
        PasswordHelper()
    }

    private var isLoginBtnVisible: Boolean
        get() = binding.loginBtn.visibility == View.VISIBLE
        set(value) {
            binding.loginBtn.visibility = if (value) View.VISIBLE else View.GONE
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Install splash screen
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Set up sign-up button click listener
        binding.signUpBtn.setOnClickListener {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(binding.fragmentContainerView.id, SignUpFragment())
                addToBackStack(null)
            }
            isLoginBtnVisible = false
        }

        // Add back stack change listener
        supportFragmentManager.addOnBackStackChangedListener {
            isLoginBtnVisible = supportFragmentManager.backStackEntryCount == 0
        }

        // Set up password visibility button
        passwordHelper.setUpPasswordVisibilityBtn(
            binding.visibilityPasswordBtn, binding.editPassword
        )

        // Set up login button click listener
        binding.loginBtn.setOnClickListener {
            if (binding.editUsername.text!!.isNotEmpty() && binding.editPassword.text!!.isNotEmpty()) {
                startActivity(Intent(this, AllNotesActivity::class.java))
            }
        }
    }
}