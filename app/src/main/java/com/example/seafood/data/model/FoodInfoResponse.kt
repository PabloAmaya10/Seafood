package com.example.seafood.data.model

import com.example.seafood.domain.model.FoodInfo
import com.google.gson.annotations.SerializedName

data class FoodInfoResponse(
    @SerializedName("foodName") val foodName: String,
    @SerializedName("foodCost") val foodCost: Int,
    @SerializedName("amount") val amount: Int
)


