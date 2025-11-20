package com.example.spotify.repository

import com.example.spotify.BooksByCategory
import com.example.spotify.entities.NewUser
import kotlinx.coroutines.flow.Flow

interface BooksRepository {
    suspend fun getBooksByCategory(category: String): BooksByCategory

    suspend fun insertUser(userName: String, email: String, password: String)

    suspend fun getUser(userName: String): NewUser

    suspend fun getAll() : Flow<List<NewUser>>

}