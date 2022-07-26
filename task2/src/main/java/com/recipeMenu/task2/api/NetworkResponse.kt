package com.recipeMenu.task2.api

//A helper class for displaying the different UI states
sealed class NetworkResponse<out T> {
    object Loading : NetworkResponse<Nothing>()
    class Success<out T>(val data: T): NetworkResponse<T>()
    class Error(val message: String): NetworkResponse<Nothing>()
}