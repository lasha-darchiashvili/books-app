package com.example.spotify.api

import com.example.spotify.BooksByCategory
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BooksApi {
    @GET("/subjects/{category}.json")
    suspend fun getBooksByCategory(@Path("category") bookType: String): Response<BooksByCategory>
}