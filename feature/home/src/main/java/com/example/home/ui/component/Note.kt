package com.example.home.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorLong
import com.example.home.R
import com.example.notedatabase.domain.entity.Note
import com.example.ui.theme.NotebookTheme
import com.example.ui.theme.Typography
import kotlinx.coroutines.delay

private const val COLOR_CHANGE_TIME = 500

@Composable
fun Note(
	modifier: Modifier = Modifier,
	id: Long,
	title: String,
	description: String,
	firstColor: Long,
	secondColor: Long,
	onClick: (Long) -> Unit,
	onColorChange: (Note) -> Unit,
) {
	var expanded by remember { mutableStateOf(false) }
	var gradientAnimate by remember { mutableStateOf(false) }
	val columnHeight by animateDpAsState(
		targetValue = if (expanded) 200.dp else 96.dp,
		animationSpec = tween(
			durationMillis = 1000,
			easing = FastOutSlowInEasing
		)
	)
	var firstColorIndex by remember { mutableIntStateOf(firstColor.toInt()) }
	var secondColorIndex by remember { mutableIntStateOf(secondColor.toInt()) }
	val newFirstColor by animateColorAsState(
		targetValue = Colors.entries[firstColorIndex].color,
		animationSpec = tween(
			durationMillis = COLOR_CHANGE_TIME,
			easing = LinearEasing
		)
	)
	val newSecondColor by animateColorAsState(
		targetValue = Colors.entries[secondColorIndex].color,
		animationSpec = tween(
			durationMillis = COLOR_CHANGE_TIME,
			easing = LinearEasing
		)
	)
	val gradient by remember(newFirstColor, newSecondColor) {
		mutableStateOf(
			Brush
				.horizontalGradient(
					colorStops = colorsToPairs(
						newFirstColor,
						newSecondColor,
					)
				)
		)
	}

	LaunchedEffect(gradientAnimate) {
		while (gradientAnimate) {
			delay(COLOR_CHANGE_TIME.toLong())
			val tmp = firstColorIndex
			firstColorIndex = secondColorIndex
			secondColorIndex = tmp
		}
		firstColorIndex = firstColor.toInt()
		secondColorIndex = secondColor.toInt()
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
			text = if (title == "") {
				stringResource(id = R.string.title_label)
			} else {
				title
			},
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

		if (columnHeight > 96.dp) {
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
								onColorChange(
									Note(
										id = id,
										title = title,
										description = description,
										firstColor = firstColorIndex.toLong(),
										secondColor = secondColorIndex.toLong(),
									)
								)
							}
					)
				}
			}

			Spacer(modifier = Modifier.padding(1.dp))

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
								onColorChange(
									Note(
										id = id,
										title = title,
										description = description,
										firstColor = firstColorIndex.toLong(),
										secondColor = secondColorIndex.toLong(),
									)
								)
							}
					)
				}
			}

			Row(
				modifier = Modifier
					.fillMaxWidth(),
				verticalAlignment = Alignment.CenterVertically,
			) {
				Checkbox(
					checked = gradientAnimate,
					onCheckedChange = {
						gradientAnimate = !gradientAnimate
					}
				)

				Text(
					text = stringResource(id = R.string.gradient_animate_checkbox)
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

private fun colorsToPairs(firstColor: Color, secondColor: Color, middleColor: Color = lerp(firstColor, secondColor, 0.5f)): Array<Pair<Float, Color>> {
	return arrayOf(
		0f to firstColor,
		0.5f to middleColor,
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
			firstColor = 0,
			secondColor = 0,
			onClick = { },
			onColorChange = { }
		)
	}
}