package com.example.notedatabase.data.mapper

import com.example.notedatabase.data.database.NoteDBEntity
import com.example.notedatabase.data.model.NoteModel
import com.example.notedatabase.domain.entity.Note

fun Note.toModel() = NoteModel(
	id = id,
	title = title,
	description = description,
	firstColor = firstColor,
	secondColor = secondColor,
)

fun List<NoteModel>.toEntity() = this.map {
	Note(
		id = it.id,
		title = it.title,
		description = it.description,
		firstColor = it.firstColor,
		secondColor = it.secondColor,
	)
}

fun NoteModel.toEntity() = Note(
	id = id,
	title = title,
	description = description,
	firstColor = firstColor,
	secondColor = secondColor,
)

fun NoteModel.toDBEntity() = NoteDBEntity(
	id = id,
	title = title,
	description = description,
	firstColor = firstColor,
	secondColor = secondColor,
)

fun List<NoteDBEntity>.toModel() = this.map {
	NoteModel(
		id = it.id,
		title = it.title,
		description = it.description,
		firstColor = it.firstColor,
		secondColor = it.secondColor,
	)
}

fun NoteDBEntity.toModel() = NoteModel(
	id = id,
	title = title,
	description = description,
	firstColor = firstColor,
	secondColor = secondColor,
)