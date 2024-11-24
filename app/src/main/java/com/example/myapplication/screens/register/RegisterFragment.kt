package com.example.myapplication.screens.register

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.red
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.CoPassViewModelFactory
import com.example.myapplication.EventObserver
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.databinding.FragmentRegisterBinding

class RegisterFragment : BaseFragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<RegisterViewModel> { CoPassViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        binding.registerNavbar.screenTitle.setText(R.string.register)
        binding.registerNavbar.backArrow.setOnClickListener {
            back()
        }

        binding.registerBtn.setOnClickListener {
            tapRegister()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
    }

    private fun tapRegister() {
        viewModel.validate()
    }

    private fun observe() {
        binding.usernameField.addTextChangedListener {
            viewModel.username.value = it.toString()
            hideError(binding.usernameField, binding.usernameWarning)
        }

        binding.emailField.addTextChangedListener {
            viewModel.email.value = it.toString()
            hideError(binding.emailField, binding.emailWarning)
        }

        binding.masterPasswordField.addTextChangedListener {
            viewModel.masterPassword.value = it.toString()
            hideError(binding.masterPasswordField, binding.masterPasswordWarning)
        }

        binding.remasterPasswordField.addTextChangedListener {
            viewModel.reMasterPassword.value = it.toString()
            hideError(binding.remasterPasswordField, binding.reMasterPasswordWarning)
        }

        viewModel.errors.observe(
            viewLifecycleOwner,
            EventObserver {
                handleError(it)
            }
        )
    }

    private fun handleError(errors: ArrayList<RegisterError>) {
        for (error in errors) {
            when(error) {
                RegisterError.BLANK_USERNAME ->
                    setError(binding.usernameField, binding.usernameWarning, error.getMessage())
                RegisterError.BLANK_EMAIL ->
                    setError(binding.emailField, binding.emailWarning, error.getMessage())
                RegisterError.BLANK_PASSWORD ->
                    setError(binding.masterPasswordField, binding.masterPasswordWarning, error.getMessage())
                RegisterError.BLANK_RE_PASSWORD ->
                    setError(binding.remasterPasswordField, binding.reMasterPasswordWarning, error.getMessage())
                RegisterError.INVALID_USERNAME ->
                    setError(binding.usernameField, binding.usernameWarning, error.getMessage())
                RegisterError.INVALID_EMAIL ->
                    setError(binding.emailField, binding.emailWarning, error.getMessage())
                RegisterError.INVALID_PASSWORD ->
                    setError(binding.masterPasswordField, binding.masterPasswordWarning, error.getMessage())
                RegisterError.UNMATCHED_PASSWORD ->
                    setError(binding.remasterPasswordField, binding.reMasterPasswordWarning, error.getMessage())
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun setError(editText: EditText, textView: TextView, message: Int) {
        editText.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
        editText.setHintTextColor(ContextCompat.getColor(requireContext(), R.color.red))
        editText.setBackgroundResource(R.drawable.error_field_shape)
        textView.setText(message)
    }
    @SuppressLint("ResourceAsColor")
    private fun hideError(editText: EditText, textView: TextView) {
        editText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        editText.setHintTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        editText.setBackgroundResource(R.drawable.field_shape)
        textView.text = ""
    }
}