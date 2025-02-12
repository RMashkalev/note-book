package com.example.home.presentation

import androidx.lifecycle.ViewModel
import com.example.home.domain.usecase.GetNotesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.InjectedParam

@KoinViewModel
class HomeViewModel(
	private val getNotesUseCase: GetNotesUseCase,
) : ViewModel() {

	private val _uiState = MutableStateFlow<HomeState>(HomeState.Initial)
	val uiState = _uiState

	fun applyIntent(intent: HomeIntent) = when(intent) {
		HomeIntent.Load      -> handleLoad()
		HomeIntent.NoteClick -> handleNoteClick()
	}

	private fun handleLoad() {
		_uiState.value = HomeState.Loading
		val notes = getNotesUseCase()
		_uiState.value = HomeState.Content(notes)
	}

	private fun handleNoteClick() {
	}
}