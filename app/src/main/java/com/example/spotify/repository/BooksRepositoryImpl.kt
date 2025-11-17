package com.example.spotify.repository

import com.example.spotify.BooksByCategory
import com.example.spotify.api.BooksApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BooksRepositoryImpl @Inject constructor(private val api: BooksApi): BooksRepository {


    override suspend fun getBooksByCategory(category: String): BooksByCategory {
        val response = api.getBooksByCategory(category)
        if(response.isSuccessful) {
            return response.body()!!
        }
        else throw Exception("error")
    }
}