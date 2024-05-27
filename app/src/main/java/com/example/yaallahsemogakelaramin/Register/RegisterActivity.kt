package com.example.yaallahsemogakelaramin.Register

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

        binding.username.addTextChangedListener {
            val text = it.toString()
            viewModel.username.value = text.toString()
            if (!viewModel.isUsernameValid()) {
                binding.username.error = "Username harus lebih dari 4 kata"
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
            viewModel.password.value = text.toString()
            if (!viewModel.isPasswordValid()) {
                binding.password.error = "Password harus lebih dari 3 kata"
            } else {
                binding.password.error = null
            }
        }
    }

}