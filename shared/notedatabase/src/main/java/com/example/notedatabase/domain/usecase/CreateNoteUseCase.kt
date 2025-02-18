package com.example.notedatabase.domain.usecase

import com.example.notedatabase.domain.entity.Note
import com.example.notedatabase.domain.repository.NoteDatabaseRepository
import org.koin.core.annotation.Factory

@Factory
class CreateNoteUseCase(
	repository: NoteDatabaseRepository
) : (Note) -> Unit by repository::create