package com.example.seafood.core.di

import com.example.seafood.core.BASE_API_REST_URL
import com.example.seafood.data.network.SeafoodApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private val logger = HttpLoggingInterceptor().also {
        it.level = HttpLoggingInterceptor.Level.BODY
    }
    private val httpClient = OkHttpClient.Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60,TimeUnit.SECONDS)
        .addInterceptor(logger)
        .addInterceptor { chain ->
            val original: Request = chain.request()
            val request: Request = original.newBuilder()
                .header("Authorization-Token", "jdnnns")
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        }
        .build()

    /**
     * Provides [Retrofit]
     * @return [Retrofit] singleton instance
     */
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder().baseUrl(BASE_API_REST_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    @Singleton
    @Provides
    fun provideSeafoodApiClient(retrofit: Retrofit): SeafoodApiClient =
        retrofit.create(SeafoodApiClient::class.java)

}