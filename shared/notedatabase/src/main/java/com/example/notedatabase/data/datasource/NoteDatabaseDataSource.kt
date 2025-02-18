package com.example.notedatabase.data.datasource

import com.example.notedatabase.data.database.NoteDatabaseDAO
import com.example.notedatabase.data.mapper.toDBEntity
import com.example.notedatabase.data.mapper.toModel
import com.example.notedatabase.data.model.NoteModel
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Single

interface NoteDatabaseDataSource {

	fun getAll(): List<NoteModel>

	fun create(note: NoteModel)

	fun update(note: NoteModel)

	fun delete(note: NoteModel)
}

@Single
class NoteDatabaseDataSourceImpl(
	private val noteDatabaseDAO: NoteDatabaseDAO,
): NoteDatabaseDataSource {

	override fun getAll(): List<NoteModel> {
		return noteDatabaseDAO.getAll().map { it.toModel() }
	}

	override fun create(note: NoteModel) {
		noteDatabaseDAO.create(note.toDBEntity())
	}

	override fun update(note: NoteModel) {
		noteDatabaseDAO.update(note.toDBEntity())
	}

	override fun delete(note: NoteModel) {
		noteDatabaseDAO.delete(note.toDBEntity())
	}
}