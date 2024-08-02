package com.example.noteapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.noteapp.databinding.ActivityMainBinding
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var splashScreen: SplashScreen

    private var currentImgIndex = 0
    private val imgResources = arrayOf(R.drawable.visible_pwd, R.drawable.invisible_pwd)

    override fun onCreate(savedInstanceState: Bundle?) {
        splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.visibilityPasswordLogin.setImageResource(imgResources[currentImgIndex])
        binding.visibilityPasswordLogin.setOnClickListener {
            // (0 + 1) % 2 du 1
            // (1 + 1) % 2 du 0
            currentImgIndex = (currentImgIndex + 1) % imgResources.size
            Log.d("currentImgIndex after click", currentImgIndex.toString())
            binding.visibilityPasswordLogin.setImageResource(imgResources[currentImgIndex])
        }
    }
}