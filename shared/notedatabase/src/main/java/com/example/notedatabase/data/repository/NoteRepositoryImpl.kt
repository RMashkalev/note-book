package com.example.notedatabase.data.repository

import com.example.notedatabase.data.datasource.NoteDatabaseDataSource
import com.example.notedatabase.data.mapper.toEntity
import com.example.notedatabase.data.mapper.toModel
import com.example.notedatabase.domain.entity.Note
import com.example.notedatabase.domain.repository.NoteDatabaseRepository
import org.koin.core.annotation.Single

@Single
class NoteRepositoryImpl(
	private val noteDatabaseDataSource: NoteDatabaseDataSource
) : NoteDatabaseRepository{

	override suspend fun getAll(): List<Note> {
		return noteDatabaseDataSource.getAll().map { it.toEntity() }
	}

	override suspend fun create(note: Note) {
		noteDatabaseDataSource.create(note.toModel())
	}

	override suspend fun update(note: Note) {
		noteDatabaseDataSource.update(note.toModel())
	}

	override suspend fun delete(note: Note) {
		noteDatabaseDataSource.delete(note.toModel())
	}

	override suspend fun getById(id: String): Note {
		return noteDatabaseDataSource.getById(id).toEntity()
	}
}