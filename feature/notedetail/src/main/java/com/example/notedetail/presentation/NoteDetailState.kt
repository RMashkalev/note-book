package com.example.notedetail.presentation

sealed interface NoteDetailState {

	data object Initial : NoteDetailState

	data object Loading : NoteDetailState

	data class Content(
		val title: String,
		val description: String,
	) : NoteDetailState

	data object Error : NoteDetailState
}