package com.example.notedatabase.domain.repository

import com.example.notedatabase.domain.entity.Note

interface NoteDatabaseRepository {

	suspend fun getAll(): List<Note>

	suspend fun create(note: Note) : Long

	suspend fun update(note: Note)

	suspend fun delete(note: Note)

	suspend fun getById(id: Long): Note
}