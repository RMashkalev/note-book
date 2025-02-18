package com.example.notedatabase.domain.repository

import com.example.notedatabase.domain.entity.Note

interface NoteDatabaseRepository {

	fun getAll(): List<Note>

	fun create(note: Note)

	fun update(note: Note)

	fun delete(note: Note)
}