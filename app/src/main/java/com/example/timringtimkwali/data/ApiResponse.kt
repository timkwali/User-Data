package com.example.timringtimkwali.data

sealed class ApiResponse<out T> {
    data class Success<out T>(val value: T): ApiResponse<T>()

    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody
    ): ApiResponse<Nothing>()

    data class ResponseBody(var message: String)
}
