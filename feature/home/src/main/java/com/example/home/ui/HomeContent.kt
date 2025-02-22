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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.home.presentation.HomeState
import com.example.ui.compose.Note
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun HomeContent(
	modifier: Modifier = Modifier,
	uiState: HomeState.Content,
	onCreateNote: () -> Unit,
	onNoteClick: (Long) -> Unit,
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
						onClick = { onNoteClick(note.id) }
					)
				}
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
	val itemsCount = 3
	val animationDuration = 1000

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