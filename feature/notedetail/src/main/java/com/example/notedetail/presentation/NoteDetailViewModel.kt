package com.example.notedetail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notedatabase.domain.entity.Note
import com.example.notedatabase.domain.usecase.DeleteNoteUseCase
import com.example.notedatabase.domain.usecase.GetNoteByIdUseCase
import com.example.notedatabase.domain.usecase.UpdateNoteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.InjectedParam

@KoinViewModel
class NoteDetailViewModel(
	private val getNoteByIdUseCase: GetNoteByIdUseCase,
	private val updateNoteUseCase: UpdateNoteUseCase,
	private val deleteNoteUseCase: DeleteNoteUseCase,
	@InjectedParam private val noteId: String,
) : ViewModel() {

	private val _uiState = MutableStateFlow<NoteDetailState>(NoteDetailState.Initial)
	val uiState = _uiState.asStateFlow()

	fun load() {
		_uiState.value = NoteDetailState.Loading
		viewModelScope.launch {
			val note = getNoteByIdUseCase(noteId)
			_uiState.value = NoteDetailState.Content(
				title = note.title,
				description = note.description,
			)
		}
	}

	fun changeTitle(title: String) {
		val currentState = _uiState.value as? NoteDetailState.Content ?: return
		_uiState.value = currentState.copy(title = title)
	}

	fun changeDescription(description: String) {
		val currentState = _uiState.value as? NoteDetailState.Content ?: return
		_uiState.value = currentState.copy(description = description)
	}

	fun saveNote() {
		val currentState = _uiState.value as? NoteDetailState.Content ?: return
		viewModelScope.launch {
			with(currentState) {
				updateNoteUseCase(
					Note(
						id = noteId.toLong(),
						title = title,
						description = description,
					)
				)
			}
		}
	}

	fun deleteNote() {
		val currentState = _uiState.value as? NoteDetailState.Content ?: return
		viewModelScope.launch {
			with(currentState) {
				deleteNoteUseCase(
					Note(
						id = noteId.toLong(),
						title = title,
						description = description,
					)
				)
			}
		}
	}
}