package com.example.yaallahsemogakelaramin

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.yaallahsemogakelaramin.Login.LoginViewModel
import com.example.yaallahsemogakelaramin.register.RegisterViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(
        private val repository: Repository,
        private val pref: UserPreference,
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterViewModel(repository, pref) as T
    }

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return LoginViewModel(repository, pref) as T
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context, pref: UserPreference): ViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: ViewModelFactory(
                            Injection.provideRepository(context),
                            pref)
                        .also { instance = it }
                }
    }
}