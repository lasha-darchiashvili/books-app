package com.example.spotify.di

import com.example.spotify.repository.BooksRepository
import com.example.spotify.repository.BooksRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsRepository(repositoryImpl: BooksRepositoryImpl): BooksRepository
}