package com.example.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.home.presentation.HomeState

@Composable
fun HomeContent(
	modifier: Modifier = Modifier,
	uiState: HomeState.Content,
) {
	Box(
		modifier = modifier
			.fillMaxSize()
			.background(Color.Cyan)
	) {
		LazyColumn {
			items(uiState.notes.size) {
				Box(modifier = Modifier
					.fillParentMaxWidth()
					.height(50.dp)
					.padding(all = 16.dp)
					.background(Color.Gray),
				) {
					Text(
						text = uiState.notes[it].text,
					)
				}
			}
		}
	}
}