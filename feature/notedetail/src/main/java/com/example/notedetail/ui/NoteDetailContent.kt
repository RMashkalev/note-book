package com.example.notedetail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notedetail.presentation.NoteDetailState
import com.example.ui.theme.NotebookTheme
import com.example.ui.theme.Typography

@Composable
fun NoteDetailContent(
	modifier: Modifier = Modifier,
	uiState: NoteDetailState.Content,
	onChangeTitle: (String) -> Unit,
	onChangeDescription: (String) -> Unit
) {
	Column(
		modifier = modifier
			.scrollable(
				state = rememberScrollState(),
				orientation = Orientation.Vertical
			)
			.padding(horizontal = 8.dp),

	) {
		Title(
			title = uiState.title,
			onChangeTitle = { onChangeTitle(it) }
		)

		Spacer(modifier = Modifier.padding(4.dp))

		Description(
			modifier = Modifier.weight(1.0f),
			description = uiState.description,
			onChangeDescription = { onChangeDescription(it) }
		)

		Spacer(modifier = Modifier.padding(2.dp))

		Button(
			modifier = Modifier
				.fillMaxWidth(),
			shape = RoundedCornerShape(8.dp),
			onClick = {	}
		) {
			Text(
				text = "Сохранить"
			)
		}
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
		modifier = Modifier
			.fillMaxWidth()
			.clip(RoundedCornerShape(8.dp)),
	)
}

@Composable
private fun Description(
	modifier: Modifier = Modifier,
	description: String,
	onChangeDescription: (String) -> Unit,
) {
	BasicTextField(
		value = description,
		onValueChange = onChangeDescription,
		textStyle = Typography.bodyLarge,
		modifier = modifier
			.fillMaxWidth()
			.clip(RoundedCornerShape(8.dp))
			.background(Color.LightGray),
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
			onChangeTitle = {},
			onChangeDescription = {}
		)
	}
}