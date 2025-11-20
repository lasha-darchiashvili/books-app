package com.example.spotify.repository

import com.example.spotify.BooksByCategory
import com.example.spotify.api.BooksApi
import com.example.spotify.daos.UsersDao
import com.example.spotify.entities.NewUser
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BooksRepositoryImpl @Inject constructor(private val api: BooksApi, private val dao: UsersDao): BooksRepository {


    override suspend fun getBooksByCategory(category: String): BooksByCategory {
        val response = api.getBooksByCategory(category)
        if(response.isSuccessful) {
            return response.body()!!
        }
        else throw Exception("error")
    }

    override suspend fun insertUser(
        userName: String,
        email: String,
        password: String
    ) {
        val newUser = NewUser(0, userName, email, password)
        dao?.insert(newUser)
    }

    override suspend fun getUser(userName: String): NewUser {
        return dao.getUserByUsername(userName)
    }

    override suspend fun getAll(): Flow<List<NewUser>> {
        return dao.getUsers()
    }
}