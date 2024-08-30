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

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.signUpBtn.setOnClickListener {
            moveToSignUpFragment()
        }

        handleBackStackToUi(binding.loginBtn)

        passwordHelper.setUpPasswordVisibilityBtn(
            binding.visibilityPasswordBtn, binding.editPassword
        )

        binding.loginBtn.setOnClickListener {
            moveToAllNotesActivity()
        }
    }

    private fun moveToAllNotesActivity() {
        if (binding.editUsername.text!!.isNotEmpty() && binding.editPassword.text!!.isNotEmpty()) {
            startActivity(Intent(this, AllNotesActivity::class.java))
        }
    }

    private fun moveToSignUpFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(binding.fragmentContainerView.id, SignUpFragment())
            addToBackStack(null)
        }
    }

    private fun handleBackStackToUi(view: View) {
        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount == 0) {
                view.visibility = View.VISIBLE
            } else {
                view.visibility = View.GONE
            }
        }
    }
}

