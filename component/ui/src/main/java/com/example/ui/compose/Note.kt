package com.example.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.theme.NotebookTheme
import com.example.ui.theme.Typography

@Composable
fun Note(
	id: Int,
	title: String,
	description: String,
	onClick: (Int) -> Unit,
) {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.height(96.dp)
			.padding(8.dp)
			.clip(RoundedCornerShape(size = 16.dp))
			.background(Color.Gray)
			.clickable { onClick(id) }
	) {
		Text(
			text = title,
			style = Typography.titleLarge,
			maxLines = 1,
			overflow = TextOverflow.Ellipsis,
			modifier = Modifier
				.padding(horizontal = 16.dp, vertical = 2.dp)
		)

		Text(
			text = description,
			maxLines = 1,
			overflow = TextOverflow.Ellipsis,
			modifier = Modifier
				.padding(horizontal = 16.dp, vertical = 2.dp)
		)
	}
}

@Preview
@Composable
private fun NotePreview() {
	NotebookTheme {
		Note(
			id = 0,
			title = "Заметка",
			description = "Описание",
			onClick = {  },
		)
	}
}