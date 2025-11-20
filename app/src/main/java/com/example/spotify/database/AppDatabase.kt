package com.example.spotify.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.spotify.daos.UsersDao
import com.example.spotify.entities.NewUser

@Database(entities = [NewUser::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun UsersDao(): UsersDao
    companion object {
        @Volatile
        private var INSTANCE: com.example.spotify.database.AppDatabase? = null

        fun getDatabase(context: Context): com.example.spotify.database.AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    com.example.spotify.database.AppDatabase::class.java,
                    "users_database"
                )
                    .build()

                INSTANCE = instance

                instance
            }
        }
    }
}