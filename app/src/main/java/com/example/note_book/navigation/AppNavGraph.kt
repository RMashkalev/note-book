package com.example.note_book.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.home.presentation.HomeViewModel
import com.example.home.ui.HomeScreen
import com.example.notedetail.ui.NoteDetailScreen
import org.koin.java.KoinJavaComponent.inject

@Composable
fun AppNavGraph() {

	val navController = rememberNavController()
	NavHost(navController = navController, startDestination = NavRoutes.HOME) {
		composable(NavRoutes.HOME) {
			HomeScreen(
				onNoteClick = { noteId ->
					navController.navigate(NavRoutes.NOTE_DETAIL + "/${noteId}")
				}
			)
		}
		composable(
			route = NavRoutes.NOTE_DETAIL + "/{noteId}",
			arguments = listOf(
				navArgument("noteId") { type = NavType.LongType },
			)
		) { stackEntry ->
			val noteId = stackEntry.arguments?.getLong("noteId")
			NoteDetailScreen(
				noteId = requireNotNull(noteId),
				onNavigateBack = { navController.navigate(NavRoutes.HOME) }
			)
		}
	}
}