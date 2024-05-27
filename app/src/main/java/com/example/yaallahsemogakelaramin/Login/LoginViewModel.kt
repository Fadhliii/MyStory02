package com.example.yaallahsemogakelaramin.Login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    val email = MutableLiveData<String>() //tentukan dulu variable sesuai dengan yang ada di layout
    val password = MutableLiveData<String>()


    fun isEmailValid(): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.value?.matches(emailPattern.toRegex()) ?: false
    }

    fun isPasswordValid(): Boolean {
        return password.value?.length ?: 0 >= 3
    }
}