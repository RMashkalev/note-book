package com.example.notedatabase.data.datasource

import com.example.notedatabase.data.database.NoteDatabaseDAO
import com.example.notedatabase.data.mapper.toDBEntity
import com.example.notedatabase.data.mapper.toModel
import com.example.notedatabase.data.model.NoteModel
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Single

interface NoteDatabaseDataSource {

	suspend fun getAll(): List<NoteModel>

	suspend fun create(note: NoteModel) : Long

	suspend fun update(note: NoteModel)

	suspend fun delete(note: NoteModel)

	suspend fun getById(id: String): NoteModel
}

@Single
class NoteDatabaseDataSourceImpl(
	private val noteDatabaseDAO: NoteDatabaseDAO,
): NoteDatabaseDataSource {

	override suspend fun getAll(): List<NoteModel> {
		return noteDatabaseDAO.getAll().toModel()
	}

	override suspend fun create(note: NoteModel) : Long {
		return noteDatabaseDAO.create(note.toDBEntity())
	}

	override suspend fun update(note: NoteModel) {
		noteDatabaseDAO.update(note.toDBEntity())
	}

	override suspend fun delete(note: NoteModel) {
		noteDatabaseDAO.delete(note.toDBEntity())
	}

	override suspend fun getById(id: String): NoteModel {
		return noteDatabaseDAO.getById(id).toModel()
	}
}