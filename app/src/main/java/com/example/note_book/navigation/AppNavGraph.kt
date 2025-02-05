package com.example.note_book.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.home.presentation.HomeViewModel
import com.example.home.ui.HomeScreen

@Composable
fun AppNavGraph(
	navController: NavHostController,
	homeViewModel: HomeViewModel,
) {
	NavHost(navController = navController, startDestination = NavRoutes.HOME) {
		composable(NavRoutes.HOME) {
			HomeScreen(
				uiState = homeViewModel.uiState,
				applyIntent = homeViewModel::applyIntent
			)
		}
	}
}