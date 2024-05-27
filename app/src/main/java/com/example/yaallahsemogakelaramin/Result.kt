package com.example.yaallahsemogakelaramin

sealed class Result<out T> {
    // TODO 1: Buatlah class Success dan Error yang merupakan turunan dari class Result
    // Success memiliki satu properti bertipe data generik T bernama data
    // Error memiliki satu properti bertipe Throwable bernama exception
    // Success dan Error merupakan turunan dari Result yang memiliki satu properti data bertipe T
    // dan exception bertipe Throwable
    // T bisa berupa data apapun, sedangkan exception adalah turunan dari Throwable.
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: String) : Result<Nothing>() // fungsi in akan mengembalikan object Error

    object Loading : Result<Nothing>() // fungsi ini akan mengembalikan object Loading

}