package com.example.note_book.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.home.presentation.HomeViewModel
import com.example.note_book.app.App
import com.example.note_book.navigation.AppNavGraph
import com.example.ui.compose.Screen
import javax.inject.Inject

class MainActivity : ComponentActivity() {

	@Inject
	lateinit var homeViewModel: HomeViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()

		(applicationContext as App).appComponent.inject(this)

		setContent {
			Screen {
				val navController = rememberNavController()
				AppNavGraph(
					navController = navController,
					homeViewModel = homeViewModel,
					)
			}
		}
	}
}