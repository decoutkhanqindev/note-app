package com.example.noteapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noteapp.databinding.FragmentSignUpBinding
import com.example.noteapp.utils.PasswordHelper

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding

    private val passwordHelper by lazy(LazyThreadSafetyMode.NONE) {
        PasswordHelper()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        passwordHelper.setUpPasswordVisibilityBtn(
            binding.visibilityPasswordBtn1, binding.editPassword
        )
        passwordHelper.setUpPasswordVisibilityBtn(
            binding.visibilityPasswordBtn2, binding.editConfirmPassword
        )
    }
}