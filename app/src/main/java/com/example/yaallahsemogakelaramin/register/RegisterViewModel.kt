package com.example.yaallahsemogakelaramin.register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yaallahsemogakelaramin.Repository
import com.example.yaallahsemogakelaramin.UserPreference


class RegisterViewModel(
        private val repository: Repository,
        pref: UserPreference,

        ) : ViewModel() {
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    suspend fun register(username: String, email: String, password: String) {
        repository.registerRepository(username, email, password) // repository

    }


    fun isUsernameValid(): Boolean {
        val usernameValue = username.value ?: return false
        // message
        return usernameValue.length >= 4 && !usernameValue.contains(" ") && usernameValue == usernameValue.toLowerCase()
    }

    fun filterUsernameInput(input: String): String {
        val filteredInput = StringBuilder()
        for (char in input) {
            if (!char.isWhitespace() && !char.isUpperCase()) {
                filteredInput.append(char)
            }
        }
        Log.e("RegisterViewModel", "filterUsernameInput: $filteredInput")
        return filteredInput.toString()
    }

    fun isPasswordValid(): Boolean {
        return password.value?.length ?: 0 >= 3

    }

    fun isEmailValid(): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.value?.matches(emailPattern.toRegex()) ?: false
    }
}