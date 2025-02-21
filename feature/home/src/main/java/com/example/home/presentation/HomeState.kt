package com.example.home.presentation

import com.example.notedatabase.domain.entity.Note

sealed interface HomeState {

	data object Initial : HomeState

	data object Loading : HomeState

	data class Content(
		val notes: List<Note>,
	) : HomeState

	data object Error : HomeState
}