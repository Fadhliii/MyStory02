package com.example.yaallahsemogakelaramin.Register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yaallahsemogakelaramin.Repository

class RegisterViewModel : ViewModel() {
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val email = MutableLiveData<String>()
//
//    private val repository = Repository( )
//
//    suspend fun register() {
//        repository.register(username.value, email.value, password.value)
//    }

    fun isUsernameValid(): Boolean {
        val usernameValue = username.value ?: return false
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