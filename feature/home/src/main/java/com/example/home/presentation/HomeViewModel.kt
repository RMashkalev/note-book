package com.example.home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notedatabase.domain.entity.Note
import com.example.notedatabase.domain.usecase.CreateNoteUseCase
import com.example.notedatabase.domain.usecase.DeleteNoteUseCase
import com.example.notedatabase.domain.usecase.GetAllNotesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel(
	private val createNoteUseCase: CreateNoteUseCase,
	private val getAllNotesUseCase: GetAllNotesUseCase,
) : ViewModel() {

	private val _uiState = MutableStateFlow<HomeState>(HomeState.Initial)
	val uiState = _uiState.asStateFlow()

	fun load() {
		_uiState.value = HomeState.Loading
		viewModelScope.launch {
			val notes = getAllNotesUseCase()

			_uiState.value = HomeState.Content(notes)
		}
	}

	fun createNote(onNavigateToNote: (Long) -> Unit) {
		val currentState = _uiState.value as? HomeState.Content ?: return

		viewModelScope.launch {
			val note = Note(
				id = 0,
				title = "Заметка",
				description = "Описание"
			)
			val noteId = createNoteUseCase(note)
			val notes = getAllNotesUseCase()
			_uiState.value = currentState.copy(notes = notes)
			onNavigateToNote(noteId)
		}
	}
}