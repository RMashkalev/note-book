package com.example.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.home.presentation.HomeIntent
import com.example.home.presentation.HomeState
import com.example.ui.compose.Note

@Composable
fun HomeContent(
	modifier: Modifier = Modifier,
	uiState: HomeState.Content,
	onCreateNote: () -> Unit,
	onNoteClick: (String) -> Unit,
) {
	Box(
		modifier = modifier
			.fillMaxSize()
	) {
		LazyColumn {
			item {
				Button(
					modifier = Modifier
						.fillMaxWidth()
						.padding(8.dp),
					onClick = {
						onCreateNote()
					},
				) {
					Text(
						text = "Создать заметку"
					)
				}
			}
			uiState.notes.forEach { note ->
				item {
					Note(
						id = note.id,
						title = note.title,
						description = note.description,
						onClick = { onNoteClick(note.id.toString()) }
					)
				}
			}

		}
	}
}