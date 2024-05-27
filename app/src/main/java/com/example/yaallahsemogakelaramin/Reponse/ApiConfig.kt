package com.example.yaallahsemogakelaramin.Reponse

import com.google.android.datatransport.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    fun getApiService(token: String): ApiService {
        // log wajib karena untuk melihat request dan response dari server API
        val loggingInterceptor = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        else
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        // mendapatkan token untuk mengakses API dan mengirimkan token ke server API
        val authInterceptor = Interceptor { chain ->
            val req = chain.request()
            val requestHeaders = req.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(requestHeaders)
        }
        // ----------------------------------------------------------------------------------------------------------- //
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor) // auth interceptor untuk menambahkan token ke header request agar bisa mengakses API
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://story-api.dicoding.dev/v1")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ApiService::class.java)
    }
}
