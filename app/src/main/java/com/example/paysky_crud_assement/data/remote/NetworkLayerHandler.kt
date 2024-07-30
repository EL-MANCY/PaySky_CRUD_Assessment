package com.example.paysky_crud_assement.data.remote

import com.example.paysky_crud_assement.data.dto.BaseErrorDTO
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

suspend fun <T> apiCallHandler(
    dispatcher: CoroutineDispatcher = Dispatchers.IO, apiCall: suspend () -> T
): ResponseWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResponseWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            handleError(throwable)
        }
    }
}

fun <T> handleError(throwable: Throwable): ResponseWrapper<T> {
    val errorString = (throwable as? HttpException)?.response()?.errorBody()?.string()
    return try {
        val baseErrorDTO = Gson().fromJson(errorString, BaseErrorDTO::class.java)
        ResponseWrapper.Error(
            message = baseErrorDTO.message
                ?: baseErrorDTO.details
                ?: "Something went wrong, Please try again later."
        )
    } catch (e: Exception) {
        val statusCode = (throwable as? HttpException)?.code() ?: 0
        if (statusCode == 500)
            ResponseWrapper.Error(message = "An error occurred, Please try again later.")
        else
            ResponseWrapper.Error(message = "Something went wrong, Please try again later.")
    }
}
