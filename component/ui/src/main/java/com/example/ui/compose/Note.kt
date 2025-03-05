package com.example.ui.compose

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.theme.NotebookTheme
import com.example.ui.theme.Typography

@Composable
fun Note(
	modifier: Modifier = Modifier,
	id: Long,
	title: String,
	description: String,
	onClick: (Long) -> Unit,
) {
	var expanded by remember { mutableStateOf(false) }
	val columnHeight by animateDpAsState(
		targetValue = if (expanded) 160.dp else 96.dp,
		animationSpec = tween(
			durationMillis = 1000,
			easing = FastOutSlowInEasing
		)
	)
	var firstColorIndex by remember { mutableIntStateOf(0) }
	var secondColorIndex by remember { mutableIntStateOf(0) }
	val firstColor by animateColorAsState(
		targetValue = Colors.entries[firstColorIndex].color,
		animationSpec = tween(
			durationMillis = 500,
			easing = LinearEasing
		)
	)
	val secondColor by animateColorAsState(
		targetValue = Colors.entries[secondColorIndex].color,
		animationSpec = tween(
			durationMillis = 500,
			easing = LinearEasing
		)
	)
	val gradient by remember(firstColor, secondColor) {
		mutableStateOf(
			Brush
				.horizontalGradient(
					colorStops = colorsToPairs(
						firstColor,
						secondColor,
					)
				)
		)
	}

	Column(
		modifier = modifier
			.fillMaxWidth()
			.height(columnHeight)
			.clip(RoundedCornerShape(size = 16.dp))
			.background(gradient)
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

		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(end = 8.dp),
			horizontalArrangement = Arrangement.End
		) {
			Icon(
				modifier = Modifier
					.clickable { expanded = !expanded },
				imageVector = Icons.Filled.Build,
				contentDescription = "Build",
			)
		}

		Spacer(
			modifier = Modifier
				.padding(top = 12.dp)
		)

		HorizontalDivider(
			color = Color.Gray
		)

		Row(
			modifier = Modifier
				.fillMaxWidth(),
		) {
			Colors.entries.forEach { color ->
				Box(
					modifier = Modifier
						.padding(start = 3.dp)
						.size(32.dp)
						.background(color = color.color)
						.clickable {
							firstColorIndex = color.ordinal
						}
				)
			}
		}

		HorizontalDivider()

		Row(
			modifier = Modifier
				.fillMaxWidth(),
		) {
			Colors.entries.forEach { color ->
				Box(
					modifier = Modifier
						.padding(start = 3.dp)
						.size(32.dp)
						.background(color = color.color)
						.clickable {
							secondColorIndex = color.ordinal
						}
				)
			}
		}
	}
}

private enum class Colors(val color: Color) {
	GRAY(Color(0xFFCCCCCC)),
	PINK(Color(0xFFF8BBD0)),
	PEACH(Color(0xFFFFCCBC)),
	LAVENDER(Color(0xFFE1BEE7)),
	MINT(Color(0xFFC8E6C9)),
	BLUE(Color(0xFFB3E5FC)),
	YELLOW(Color(0xFFFFF9C4)),
	CORAL(Color(0xFFFFAB91)),
	LILAC(Color(0xFFD1C4E9)),
	GREEN(Color(0xFFDCEDC8)),
	SAND(Color(0xFFFFF3E0))
}

private fun colorsToPairs(firstColor: Color, secondColor: Color): Array<Pair<Float, Color>> {
	return arrayOf(
		0f to firstColor,
		1f to secondColor,
	)
}

@Preview
@Composable
private fun NotePreview() {
	NotebookTheme {
		Note(
			id = 0,
			title = "Заметка",
			description = "Описание",
			onClick = { },
		)
	}
}