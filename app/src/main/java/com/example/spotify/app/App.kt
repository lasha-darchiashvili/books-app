package com.example.spotify.app

import android.app.Application
import com.example.spotify.database.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {
    val dataBase : AppDatabase by lazy {
        AppDatabase.getDatabase(context = this)
    }
}