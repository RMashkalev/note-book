package com.example.home.ui

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.example.home.presentation.HomeIntent
import com.example.home.presentation.HomeState
import com.example.home.presentation.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
	viewModel: HomeViewModel = koinViewModel(),
	onNoteClick: (String) -> Unit,
) {
	val uiState = viewModel.uiState.collectAsState()

	LaunchedEffect(key1 = true) {
		viewModel.applyIntent(HomeIntent.Load)
	}
	Scaffold(
		topBar = {
			CenterAlignedTopAppBar(
				title = {
					Text(
						"Список",
						maxLines = 1,
						overflow = TextOverflow.Ellipsis
					)
				},
				modifier = Modifier,
			)
		}
	) { paddingValues ->
		when (uiState.value) {
			is HomeState.Content -> {
				HomeContent(
					modifier = Modifier.padding(paddingValues = paddingValues),
					uiState = uiState.value as HomeState.Content,
					onCreateNote = { viewModel.applyIntent(HomeIntent.CreateNote) },
					onNoteClick = onNoteClick,
				)
			}

			is HomeState.Error   -> Unit
			is HomeState.Initial -> Unit
			is HomeState.Loading -> Unit
		}

	}
}