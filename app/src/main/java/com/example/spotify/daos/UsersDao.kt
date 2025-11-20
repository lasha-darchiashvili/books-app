package com.example.spotify.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.spotify.entities.NewUser
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: NewUser)
    @Query("SELECT * FROM registeredUsers WHERE userName = :username")
    fun getUserByUsername(username: String): NewUser
    @Query("SELECT * FROM registeredUsers")
    fun getUsers(): Flow<List<NewUser>>
}