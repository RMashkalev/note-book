package com.example.notedetail.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.notedetail.presentation.NoteDetailState
import com.example.notedetail.presentation.NoteDetailViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NoteDetailScreen(
	viewModel: NoteDetailViewModel = koinViewModel()
) {
	val uiState = viewModel.uiState.collectAsState()

	Scaffold { paddingValues ->
		when(uiState.value) {
			is NoteDetailState.Content -> {
				NoteDetailContent(
					modifier = Modifier.padding(paddingValues = paddingValues),
					uiState = uiState.value as NoteDetailState.Content,
					applyIntent = { viewModel.applyIntent(it) }
				)
			}
			is NoteDetailState.Error -> Unit
			is NoteDetailState.Initial -> Unit
			is NoteDetailState.Loading -> Unit
		}

	}
}