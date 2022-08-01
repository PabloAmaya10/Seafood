package com.example.seafood.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.seafood.data.database.dao.SeafoodDao
import com.example.seafood.data.database.entities.SeafoodEntity

@Database(entities = [SeafoodEntity :: class], version = 1)
abstract class SeafoodDataBase:RoomDatabase() {

    abstract fun getSeafoodDao(): SeafoodDao
}