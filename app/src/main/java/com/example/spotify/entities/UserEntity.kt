package com.example.spotify.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "registeredUsers")
data class NewUser(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userName: String,
    val email: String,
    val password: String
)