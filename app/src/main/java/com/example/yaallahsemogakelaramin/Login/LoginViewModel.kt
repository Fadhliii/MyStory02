package com.example.yaallahsemogakelaramin.Login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    val username = MutableLiveData<String>() //tentukan dulu variable sesuai dengan yang ada di layout
    val password = MutableLiveData<String>()

    fun isUsernameValid(): Boolean {
        return username.value?.length ?: 0 >= 4
    }

    fun isPasswordValid(): Boolean {
        return password.value?.length ?: 0 >= 3
    }
}