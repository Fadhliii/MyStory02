package com.example.yaallahsemogakelaramin

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.yaallahsemogakelaramin.Reponse.ApiConfig
import com.example.yaallahsemogakelaramin.Reponse.ApiService
import kotlinx.coroutines.runBlocking

val Context.datastore :  DataStore<Preferences> by preferencesDataStore(name = "user_session")
object Injection {
    fun provideRepository(context: Context): Repository {
        val pref = UserPreference.getInstance(context.datastore)
        val token = runBlocking { pref.getToken() } //runblocking berguna untuk menjalankan kode secara blocking untuk mendapatkan token
        val apiService = ApiConfig.getApiService(token.toString())

        return Repository.getInstance(apiService, pref)
    }
}