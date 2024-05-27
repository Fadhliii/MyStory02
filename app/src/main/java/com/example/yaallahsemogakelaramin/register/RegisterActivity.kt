package com.example.yaallahsemogakelaramin.register

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.yaallahsemogakelaramin.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener{
            setupRegister()
        }

        binding.username.addTextChangedListener {
            val missingChars =viewModel.isEmailValid()
            val text = it.toString()
            viewModel.username.value = text.toString()
            if (!viewModel.isUsernameValid()) {
                binding.username.error = "Username harus lebih dari $missingChars karakter"
            }
            else {
                binding.username.error = null
            }
            // Filter username input
            val input = text.toString()
            val filteredInput = viewModel.filterUsernameInput(input)
            if (input != filteredInput) {

                binding.username.setText(filteredInput)
                binding.username.setSelection(filteredInput.length) // Set cursor to the end of the text
            }
        }
        binding.email.addTextChangedListener { text ->
            viewModel.email.value = text.toString()
            if (!viewModel.isEmailValid()) {
                binding.email.error = "Email tidak valid"
            } else {
                binding.email.error = null
            }
        }
        binding.password.addTextChangedListener { text ->
            val missingPassword = viewModel.isPasswordValid()
            viewModel.password.value = text.toString()
            if (!viewModel.isPasswordValid()) {
                binding.password.error = "Password harus lebih dari $missingPassword kata"
            } else {
                binding.password.error = null
            }
        }
    }

    private fun setupRegister() {
        // set register
        val name = binding.username.text.toString()
        val email = binding.email.text.toString()
        val password = binding.password.text.toString() //

    }

}