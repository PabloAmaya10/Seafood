package com.example.seafood.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.seafood.domain.model.FoodInfo

@Entity(tableName = "seafood_table")
data class SeafoodEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "foodName") val foodName: String,
    @ColumnInfo(name = "foodCost") val foodCost: Int,
    @ColumnInfo(name = "amount") val amount: Int
)

fun FoodInfo.toEntity(): SeafoodEntity = SeafoodEntity(foodName = foodName, foodCost = foodCost, amount = amount)
