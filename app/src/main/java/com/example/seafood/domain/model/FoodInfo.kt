package com.example.seafood.domain.model

import com.example.seafood.data.model.FoodInfoResponse
import com.example.seafood.data.database.entities.SeafoodEntity


data class FoodInfo(
    val foodName: String,
    val foodCost: Int,
    var amount: Int
)

fun FoodInfoResponse.toModel(): FoodInfo = FoodInfo(foodName, foodCost, amount)

fun SeafoodEntity.toModel(): FoodInfo = FoodInfo(foodName, foodCost, amount)
