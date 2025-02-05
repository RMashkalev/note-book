package com.example.home.ui

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.home.presentation.HomeIntent
import com.example.home.presentation.HomeState

@Composable
fun HomeScreen(
	uiState: HomeState,
	applyIntent: (HomeIntent) -> Unit,
) {
	Log.d("tag", "recompose $uiState")
	Scaffold { paddingValues ->
		when(uiState) {
			is HomeState.Content -> {
				HomeContent(
					modifier = Modifier.padding(paddingValues = paddingValues),
					uiState = uiState,
				)
			}
			is HomeState.Error -> {  }
			is HomeState.Initial -> { applyIntent(HomeIntent.Load) }
			is HomeState.Loading -> {  }
		}

	}
}