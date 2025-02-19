package com.example.notedetail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.notedetail.presentation.NoteDetailIntent
import com.example.notedetail.presentation.NoteDetailState
import com.example.ui.theme.NotebookTheme

@Composable
fun NoteDetailContent(
	modifier: Modifier = Modifier,
	uiState: NoteDetailState.Content,
	applyIntent: (NoteDetailIntent) -> Unit,
) {
	Column {
		Title(
			title = uiState.title,
			onChangeTitle = { applyIntent(NoteDetailIntent.ChangeTitle(it)) }
		)

		Description(
			description = uiState.title,
			onChangeDescription = { applyIntent(NoteDetailIntent.ChangeDescription(it)) }
		)
	}
}

@Composable
private fun Title(
	title: String,
	onChangeTitle: (String) -> Unit,
) {
	TextField(
		value = title,
		onValueChange = onChangeTitle,
	)
}

@Composable
private fun Description(
	description: String,
	onChangeDescription: (String) -> Unit,
) {
	TextField(
		value = description,
		onValueChange = onChangeDescription,
	)
}

@Preview
@Composable
private fun NoteDetailContentPreview() {
	val uiState = NoteDetailState.Content(
		title = "Заметка",
		description = "Описание"
	)

	NotebookTheme {
		NoteDetailContent(
			modifier = Modifier,
		    uiState = uiState,
			applyIntent = {},
		)
	}
}