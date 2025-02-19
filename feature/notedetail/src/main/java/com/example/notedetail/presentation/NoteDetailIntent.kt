package com.example.notedetail.presentation

sealed interface NoteDetailIntent {

	data class ChangeTitle(val title: String) : NoteDetailIntent

	data class ChangeDescription(val description: String) : NoteDetailIntent

	data object NavigateBack : NoteDetailIntent
}