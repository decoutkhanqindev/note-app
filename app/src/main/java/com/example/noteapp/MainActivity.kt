package com.example.noteapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.commit
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.utils.PasswordHelper

class MainActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val passwordHelper by lazy(LazyThreadSafetyMode.NONE) {
        PasswordHelper()
    }

    private var isLoginBtnVisible: Boolean
        get() = binding.loginBtn.visibility == View.VISIBLE
        set(value) {
            binding.loginBtn.visibility = if (value) View.VISIBLE else View.GONE
        }

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
            isLoginBtnVisible = false
        }

        supportFragmentManager.addOnBackStackChangedListener {
            isLoginBtnVisible = supportFragmentManager.backStackEntryCount == 0
        }

        passwordHelper.setUpPasswordVisibilityBtn(
            binding.visibilityPasswordBtn, binding.editPassword
        )

        binding.loginBtn.setOnClickListener {
            if (binding.editUsername.text!!.isNotEmpty() && binding.editPassword.text!!.isNotEmpty()) {
                startActivity(Intent(this, AllNotesActivity::class.java))
            }
        }
    }
}