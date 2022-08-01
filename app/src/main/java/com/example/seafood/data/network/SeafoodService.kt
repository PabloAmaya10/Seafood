package com.example.seafood.data.network

import com.example.seafood.core.models.SuccessResult
import com.example.seafood.core.models.Error
import com.example.seafood.core.models.Result
import com.example.seafood.data.model.FoodInfoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class SeafoodService @Inject constructor(private val api: SeafoodApiClient) {

    suspend fun getSeafoodService(): Result<List<FoodInfoResponse>> = withContext(Dispatchers.IO) {
        api.getSeafoodInformation().executeWithResult()
    }
}

private fun <T> Response<T>.executeWithResult(): Result<T> {
    return try {
        if (this.isSuccessful || this.body() != null) {
            this.body()?.let {
                SuccessResult(it)
            } ?: kotlin.run {
                createNetworkError(this)
            }
        } else {
            createNetworkError(this)
        }
    } catch (e: Exception) {
        Error.createWithException("Unable to complete request ", e)
    }
}

private fun <T> createNetworkError(response: Response<T>): Error<T> {
    val description =
        "Código: ${response.code()}. Error: ${response.errorBody() ?: "Sin cuerpo de error"}"
    return Error.networkError(description)
}
