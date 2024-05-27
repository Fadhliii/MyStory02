package com.example.yaallahsemogakelaramin.Reponse

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    suspend fun login(
            @Field("email") username: String,
            @Field("password") password: String
    ): LoginResponse

    @FormUrlEncoded
    @POST("register")
    suspend fun serviceRegister(
            @Field("username") username: String?,
            @Field("email") email: String?,
            @Field("password") password: String?
    ): GeneralResponse
}