package com.example.seafood.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.seafood.data.database.entities.SeafoodEntity

@Dao
interface SeafoodDao : BaseDao<SeafoodEntity> {
    @Query("SELECT * FROM seafood_table")
    suspend fun getAllSeafood(): List<SeafoodEntity>

    @Query("DELETE FROM seafood_table")
    suspend fun deleteAllSeafood()
}