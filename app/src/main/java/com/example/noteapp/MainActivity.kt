package com.example.noteapp

import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.commit
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.utils.PasswordHelper

class MainActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var passwordHelper: PasswordHelper

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
            binding.loginBtn.visibility = View.GONE
        }

        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount == 0) {
                binding.loginBtn.visibility = View.VISIBLE
            }
        }

        passwordHelper.setUpPasswordVisibilityBtn(
            binding.visibilityPasswordBtn, binding.editPassword
        )
    }
}