package com.example.appjetpackcompose.navigation


import HomeScreen
import LoginScreen
import androidx.compose.runtime.Composable

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appjetpackcompose.screens.OrderDetailsScreen
import com.example.appjetpackcompose.screens.OrdersScreen
import com.example.appjetpackcompose.screens.ProfileScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController = navController)
        }

        composable("home") {
            HomeScreen(navController = navController)
        }

        composable("orders") {
            OrdersScreen(navController = navController)
        }

        composable("orderDetails/{orderId}") { backStackEntry ->
            val orderId = backStackEntry.arguments?.getString("orderId")?.toIntOrNull() ?: 0
            OrderDetailsScreen(navController = navController, orderId = orderId)
        }

        composable("profile") {
            ProfileScreen(navController = navController)
        }
    }
}