package com.example.home.domain.usecase

import com.example.home.domain.entity.Note
import com.example.home.domain.repository.HomeRepository
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam

@Factory
class GetNotesUseCase(
	repository: HomeRepository
) : () -> List<Note> by repository::getNotes