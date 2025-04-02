package com.example.home.ui

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.home.R
import com.example.home.presentation.HomeState
import com.example.home.ui.component.Note
import com.example.notedatabase.domain.entity.Note
import com.example.ui.compose.Screen
import kotlinx.coroutines.launch

@Composable
fun HomeContent(
	modifier: Modifier = Modifier,
	uiState: HomeState.Content,
	onCreateNote: () -> Unit,
	onNoteClick: (Long) -> Unit,
	onColorChange: (Note) -> Unit,
) {
	val lazyListState = rememberLazyListState()
	val showScrollButton = remember {
		derivedStateOf {
			lazyListState.firstVisibleItemIndex > 5
		}
	}
	val coroutineScope = rememberCoroutineScope()

	Box(
		modifier = modifier
			.fillMaxSize()
			.padding(8.dp),
	) {
		LazyColumn(
			state = lazyListState
		) {
			uiState.notes.forEach { note ->
				item {
					Note(
						modifier = Modifier
							.padding(bottom = 8.dp),
						id = note.id,
						title = note.title,
						description = note.description,
						firstColor = note.firstColor,
						secondColor = note.secondColor,
						onClick = { onNoteClick(note.id) },
						onColorChange = { onColorChange(it) }
					)
				}
			}
		}

		if (uiState.notes.isEmpty()) {
			Text(
				text = stringResource(id = R.string.empty_list_message),
				textAlign = TextAlign.Center,
				modifier = Modifier
					.align(Alignment.Center),
			)
		}

		if (showScrollButton.value) {
			Box(
				modifier = Modifier
					.size(72.dp)
					.align(Alignment.BottomStart)
					.clip(RoundedCornerShape(72.dp))
					.background(Color.DarkGray)
					.border(2.dp, Color.Gray, RoundedCornerShape(72.dp))
					.clickable {
						coroutineScope.launch {
							lazyListState.animateScrollToItem(0)
						}
					},
				contentAlignment = Alignment.Center
			) {
				Icon(
					imageVector = Icons.Filled.KeyboardArrowUp,
					contentDescription = "Localized description"
				)
			}
		}

		DropDownButton(
			modifier = Modifier
				.align(Alignment.BottomEnd),
			onCreateNote = onCreateNote,
		)
	}
}

@Composable
private fun DropDownButton(
	modifier: Modifier,
	onCreateNote: () -> Unit
) {
	val itemsCount = remember { 3 }
	val animationDuration = remember { 1000 }

	var expanded by remember { mutableStateOf(false) }
	val boxHeight by animateDpAsState(
		targetValue = if (expanded) (72 * itemsCount).dp + 72.dp else 72.dp,
		animationSpec = tween(
			durationMillis = animationDuration,
			easing = FastOutSlowInEasing
		)
	)
	val iconRotation by animateFloatAsState(
		targetValue = if (expanded) 360f else 0f,
		animationSpec = tween(
			durationMillis = animationDuration,
			easing = FastOutSlowInEasing
		)
	)

	Box(
		modifier = modifier
			.width(72.dp)
			.height(boxHeight)
			.clip(RoundedCornerShape(size = 72.dp))
			.background(Color.LightGray)
			.border(2.dp, Color.Gray, RoundedCornerShape(72.dp))
			.clickable { },
	) {
		Column {
			IconButton(
				modifier = Modifier
					.size(72.dp)
					.padding(8.dp)
					.clip(RoundedCornerShape(72.dp))
					.background(Color.LightGray)
					.border(2.dp, Color.Gray, RoundedCornerShape(72.dp)),
				onClick = {
					onCreateNote()
					expanded = !expanded
				}
			) {
				Icon(
					imageVector = Icons.Filled.DateRange,
					contentDescription = "Localized description"
				)
			}

			IconButton(
				modifier = Modifier
					.size(72.dp)
					.padding(8.dp)
					.clip(RoundedCornerShape(72.dp))
					.background(Color.LightGray)
					.border(2.dp, Color.Gray, RoundedCornerShape(72.dp)),
				onClick = {
					onCreateNote()
					expanded = !expanded
				}
			) {
				Icon(
					imageVector = Icons.Filled.Info,
					contentDescription = "Localized description"
				)
			}

			IconButton(
				modifier = Modifier
					.size(72.dp)
					.padding(8.dp)
					.clip(RoundedCornerShape(72.dp))
					.background(Color.LightGray)
					.border(2.dp, Color.Gray, RoundedCornerShape(72.dp)),
				onClick = {
					onCreateNote()
					expanded = !expanded
				}
			) {
				Icon(
					imageVector = Icons.Filled.AddCircle,
					contentDescription = "Localized description"
				)
			}
		}

		Box(
			modifier = Modifier
				.size(72.dp)
				.align(Alignment.BottomEnd)
				.clip(RoundedCornerShape(72.dp))
				.background(Color.DarkGray)
				.clickable { expanded = !expanded },
		) {
			Canvas(
				modifier = Modifier
					.size(64.dp)
					.align(Alignment.Center)
			) {
				drawArc(
					color = Color.White,
					startAngle = 130f,
					sweepAngle = iconRotation,
					useCenter = false,
					style = Stroke(width = 2.dp.toPx())
				)
			}
			Icon(
				modifier = Modifier
					.align(Alignment.Center)
					.graphicsLayer {
						rotationZ = iconRotation
					},
				imageVector = Icons.Filled.Create,
				contentDescription = "Localized description"
			)
		}

	}

}

@Preview
@Composable
private fun EmptyListPreview() {
	val uiState = HomeState.Content(
		notes = listOf()
	)
	Screen {
		HomeContent(
			uiState = uiState,
			onCreateNote = {},
			onNoteClick = {},
			onColorChange = {}
		)
	}
}

@Preview
@Composable
private fun HomePreview() {
	val uiState = HomeState.Content(
		notes = listOf(
			Note(
				id = 0,
				title = "Заметка1",
				description = "Короткое описание1",
				firstColor = 0xFFB3E5FC,
				secondColor = 0xFFFFF9C4,
			),
			Note(
				id = 1,
				title = "Заметка2",
				description = "Короткое описание2",
				firstColor = 0xFFB3E5FC,
				secondColor = 0xFFFFF9C4,
			),
			Note(
				id = 2,
				title = "Заметка3",
				description = "Короткое описание3",
				firstColor = 0xFFB3E5FC,
				secondColor = 0xFFFFF9C4,
			),
			Note(
				id = 3,
				title = "Заметка4",
				description = "Короткое описание4",
				firstColor = 0xFFB3E5FC,
				secondColor = 0xFFFFF9C4,
			),
			Note(
				id = 4,
				title = "Заметка5",
				description = "Короткое описание5",
				firstColor = 0xFFB3E5FC,
				secondColor = 0xFFFFF9C4,
			),
		)
	)
	Screen {
		HomeContent(
			uiState = uiState,
			onCreateNote = {},
			onNoteClick = {},
			onColorChange = {}
		)
	}
}