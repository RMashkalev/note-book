package com.example.note_book.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.home.presentation.HomeViewModel
import com.example.home.ui.HomeScreen
import org.koin.java.KoinJavaComponent.inject

@Composable
fun AppNavGraph() {

	val navController = rememberNavController()
	NavHost(navController = navController, startDestination = NavRoutes.HOME) {
		composable(NavRoutes.HOME) {
			HomeScreen()
		}
	}
}