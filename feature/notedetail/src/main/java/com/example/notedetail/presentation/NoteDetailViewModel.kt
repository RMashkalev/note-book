package com.example.notedetail.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class NoteDetailViewModel(): ViewModel() {

	private val _uiState = MutableStateFlow<NoteDetailState>(NoteDetailState.Initial)
	val uiState = _uiState

	fun applyIntent(intent: NoteDetailIntent) = when(intent) {
		is NoteDetailIntent.ChangeTitle       -> handleChangeTitle(intent.title)
		is NoteDetailIntent.ChangeDescription -> handleChangeDescription(intent.description)
		is NoteDetailIntent.NavigateBack      -> handleNavigateBack()
	}

	private fun handleChangeTitle(title: String) {
		val currentState = _uiState.value as? NoteDetailState.Content ?: return
		_uiState.value = currentState.copy(title = title)
	}

	private fun handleChangeDescription(description: String) {
		val currentState = _uiState.value as? NoteDetailState.Content ?: return
		_uiState.value = currentState.copy(description = description)
	}

	private fun handleNavigateBack() {

	}
}