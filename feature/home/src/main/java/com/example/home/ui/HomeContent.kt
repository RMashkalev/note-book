package com.example.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
			.padding(8.dp),
	) {
		LazyColumn {
			uiState.notes.forEach { note ->
				item {
					Note(
						modifier = Modifier
							.padding(bottom = 8.dp),
						id = note.id,
						title = note.title,
						description = note.description,
						onClick = { onNoteClick(note.id.toString()) }
					)
				}
			}
		}
		
		Button(
			modifier = Modifier
				.size(72.dp)
				.align(Alignment.BottomEnd),
			onClick = {
				onCreateNote()
			},
		) {
			Icon(
				imageVector = Icons.Filled.Create,
				contentDescription = "Localized description"
			)
		}
	}
}