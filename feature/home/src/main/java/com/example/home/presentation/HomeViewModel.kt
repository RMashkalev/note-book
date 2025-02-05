package com.example.home.presentation

import com.example.home.domain.usecase.GetNotesUseCase
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel(
	private val getNotesUseCase: GetNotesUseCase,
) {

	private val _uiState = MutableStateFlow<HomeState>(HomeState.Initial)
	val uiState = _uiState.value

	fun applyIntent(intent: HomeIntent) = when(intent) {
		HomeIntent.Load      -> handleLoad()
		HomeIntent.NoteClick -> handleNoteClick()
	}

	private fun handleLoad() {
		val notes = getNotesUseCase()
		_uiState.value = HomeState.Content(notes)
	}

	private fun handleNoteClick() {
	}
}