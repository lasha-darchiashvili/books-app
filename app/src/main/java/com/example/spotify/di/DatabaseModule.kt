package com.example.spotify.di

import android.content.Context
import androidx.room.Room
import com.example.spotify.daos.UsersDao
import com.example.spotify.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            com.example.spotify.database.AppDatabase::class.java,
            "users_database"
        )
            .build()
    }

    @Provides
    fun provideMovieDao(db: AppDatabase ): UsersDao {
        return db.UsersDao()
    }
}