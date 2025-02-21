package com.example.notedetail.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.example.notedetail.presentation.NoteDetailState
import com.example.notedetail.presentation.NoteDetailViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailScreen(
	noteId: Long,
	viewModel: NoteDetailViewModel = koinViewModel(
		parameters = { parametersOf(noteId) }
	),
	onNavigateBack: () -> Unit,
) {
	val uiState = viewModel.uiState.collectAsState()

	LaunchedEffect(key1 = true) {
		viewModel.load()
	}
	Scaffold(
		topBar = {
			CenterAlignedTopAppBar(
				title = {
					Text(
						"Детали",
						maxLines = 1,
						overflow = TextOverflow.Ellipsis
					)
				},
				navigationIcon = {
					IconButton(onClick = { onNavigateBack() }) {
						Icon(
							imageVector = Icons.AutoMirrored.Filled.ArrowBack,
							contentDescription = "Localized description"
						)
					}
				},
				actions = {
					IconButton(onClick = {
						viewModel.deleteNote()
						onNavigateBack()
					}) {
						Icon(
							imageVector = Icons.Filled.Delete,
							contentDescription = "Localized description"
						)
					}
				}
			)
		}
	) { paddingValues ->
		when(uiState.value) {
			is NoteDetailState.Content -> {
				NoteDetailContent(
					modifier = Modifier.padding(paddingValues = paddingValues),
					uiState = uiState.value as NoteDetailState.Content,
					onChangeTitle = { viewModel.changeTitle(it) },
					onChangeDescription = { viewModel.changeDescription(it) },
					onSaveNote = { viewModel.saveNote() },
					onNavigateBack = { onNavigateBack() }
				)
			}
			is NoteDetailState.Error -> Unit
			is NoteDetailState.Initial -> Unit
			is NoteDetailState.Loading -> Unit
		}

	}
}