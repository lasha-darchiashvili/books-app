package com.example.spotify.repository

import com.example.spotify.BooksByCategory

interface BooksRepository {
    suspend fun getBooksByCategory(category: String): BooksByCategory
}