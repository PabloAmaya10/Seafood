package com.example.seafood.core.models

sealed class Result<T> {}

class SuccessResult<T>(val result: T) : Result<T>()

data class Error<T>(
    val code: String,
    val description: String,
    val innerError: List<Error<*>> = emptyList(),
    val exception: Throwable? = null
) : Result<T>() {
    companion object {

        @JvmStatic
        fun <T> createWithException(description: String, exception: Throwable?): Error<T> {
            return Error(ErrorCodes.INTERNAL_ERROR, description, emptyList(), exception)
        }

        @JvmStatic
        fun <T> networkError(description: String): Error<T> {
            return Error(ErrorCodes.NETWORK_ERROR, description, emptyList(), null)
        }

        @JvmStatic
        fun <T> generateErrorStr(e: Error<T>): String {
            var errorStr = e.description
            e.innerError.forEach {
                errorStr += "\n" + it.description
            }
            return errorStr
        }
    }
}

class ErrorCodes {
    companion object {
        const val INTERNAL_ERROR = "INTERNAL_ERROR"
        const val NETWORK_ERROR = "NETWORK_ERROR"
    }
}
