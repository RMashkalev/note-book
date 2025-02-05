package com.example.home.presentation

sealed interface HomeIntent {

	data object Load : HomeIntent

	data object NoteClick : HomeIntent
}