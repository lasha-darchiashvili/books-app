package com.example.spotify.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spotify.composables.Dashboard
import com.example.spotify.composables.LoginScreen
import com.example.spotify.composables.MainScreen
import com.example.spotify.composables.Register

@Composable
fun MyNavigationHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Home) {
        composable<Login> {
            LoginScreen(navController = navController)
        }
        composable<Home> {
            MainScreen(navController = navController)
        }
        composable<Register> {
            Register(navController = navController)
        }
        composable<Dashboard> {
            Dashboard()
        }
    }
}