package com.example.home.domain.usecase

import com.example.home.domain.entity.Note
import com.example.home.domain.repository.HomeRepository

class GetNotesUseCase(
	repository: HomeRepository
) : () -> List<Note> by repository::getNotes