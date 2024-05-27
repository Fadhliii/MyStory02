package com.example.yaallahsemogakelaramin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.yaallahsemogakelaramin.Reponse.ApiService
import com.example.yaallahsemogakelaramin.Reponse.GeneralResponse
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException

/**
 * Repository class that handles user registration.
 *
 * @property apiService The service used to make API calls.
 */
class Repository private constructor(
        private val apiService: ApiService,
        private val userPreference: UserPreference,

        ) {
    /**
     * Function to register a new user.
     *
     * This function makes an API call to register a new user with the provided name, email, and password.
     * It emits a Result.Loading before making the API call.
     * If the API call is successful, it emits a Result.Success with the response.
     * If an HttpException occurs during the API call, it emits a Result.Error with the error message from the response.
     * If any other Exception occurs during the API call, it emits a Result.Error with the exception's message.
     *
     * @param name The name of the user to register.
     * @param email The email of the user to register.
     * @param password The password of the user to register.
     * @return A LiveData containing a Result. The Result is Success if the API call is successful, Error otherwise.
     */

    //====================================================================================================
    private val token: String? = null // This is the token used for authentication later on in login

    fun registerRepository(name: String, email: String, password: String):
            LiveData<Result<GeneralResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.serviceRegister(name, email, password)
            emit(Result.Success(response))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string() // Retrieve the error body from the response
            val errorResponse = Gson().fromJson(errorBody, GeneralResponse::class.java)
            emit(Result.Error(errorResponse.message.toString()))
        } catch (e: Exception) {
            Log.d(TAG, "register: ${e.message}")
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {
        private const val TAG = "Repository" // This is the tag used for logging
        private var instance: Repository? = null // This is the singleton instance of the Repository
        fun getInstance(
                apiService: ApiService,
                userPreference: UserPreference,
        )
                : Repository {
                return instance ?: synchronized(this) {
                instance ?: Repository(apiService, userPreference).also { instance = it }
            }
        }
    }
}