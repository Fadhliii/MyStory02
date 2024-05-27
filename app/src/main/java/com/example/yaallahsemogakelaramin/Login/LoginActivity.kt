package com.example.yaallahsemogakelaramin.Login

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.yaallahsemogakelaramin.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.email.addTextChangedListener { text ->
            viewModel.email.value = text.toString()
            if (!viewModel.isEmailValid()) {
                binding.email.error = "Username harus minimal 4 huruf"
            } else {
                binding.email.error = null
            }
        }

        binding.password.addTextChangedListener { text ->
            viewModel.password.value = text.toString()
            if (!viewModel.isPasswordValid()) {
                binding.password.error = "Password harus minimal 3 huruf"
            } else {
                binding.password.error = null
            }
        }
    }
}