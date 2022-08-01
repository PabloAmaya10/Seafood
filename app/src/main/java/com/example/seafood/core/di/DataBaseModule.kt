package com.example.seafood.core.di

import android.content.Context
import androidx.room.Room
import com.example.seafood.core.DATABASE_NAME
import com.example.seafood.data.database.SeafoodDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent:: class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context,SeafoodDataBase::class.java,DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideSeafoodDao(db:SeafoodDataBase) = db.getSeafoodDao()

}