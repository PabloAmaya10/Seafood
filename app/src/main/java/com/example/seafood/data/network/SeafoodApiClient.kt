package com.example.seafood.data.network

import com.example.seafood.core.GET_SEAFOOD
import com.example.seafood.data.model.FoodInfoResponse
import retrofit2.Response
import retrofit2.http.GET

interface SeafoodApiClient {
    @GET(GET_SEAFOOD)
    suspend fun getSeafoodInformation(): Response<List<FoodInfoResponse>>
}