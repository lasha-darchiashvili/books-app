package com.example.spotify.di

import com.example.spotify.api.BooksApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton
import kotlinx.serialization.json.Json

val json = Json { ignoreUnknownKeys = true }


@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideApi(): Retrofit {
        return Retrofit.Builder().baseUrl("https://openlibrary.org/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): BooksApi= retrofit.create(BooksApi::class.java)

}