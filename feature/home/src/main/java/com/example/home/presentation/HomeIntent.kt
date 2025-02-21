package com.example.home.presentation

sealed interface HomeIntent {

	data object Load : HomeIntent

	data object CreateNote : HomeIntent

	data class NoteClick(
		val id: Int,
	) : HomeIntent
}