package com.example.notedatabase.domain.repository

import com.example.notedatabase.domain.entity.Note

interface NoteDatabaseRepository {

	suspend fun getAll(): List<Note>

	suspend fun create(note: Note)

	suspend fun update(note: Note)

	suspend fun delete(note: Note)

	suspend fun getById(id: String): Note
}