package com.example.noteapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noteapp.databinding.FragmentSignUpBinding
import com.example.noteapp.utils.PasswordHelper

class SignUpFragment : Fragment() {
  private var _binding: FragmentSignUpBinding? = null
  private val binding: FragmentSignUpBinding get() = _binding!!
  
  private val passwordHelper: PasswordHelper by lazy(LazyThreadSafetyMode.NONE) {
    PasswordHelper()
  }
  
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View {
    _binding = FragmentSignUpBinding.inflate(inflater, container, false)
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
    
    binding.signInBtn.setOnClickListener {
      requireActivity().supportFragmentManager.popBackStack()
    }
    
    binding.backBtn.setOnClickListener {
      requireActivity().supportFragmentManager.popBackStack()
    }
  }
  
  override fun onDestroyView() {
    _binding = null
    super.onDestroyView()
  }
}