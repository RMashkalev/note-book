package com.example.notedatabase.data.mapper

import android.util.Log
import com.example.notedatabase.data.database.NoteDBEntity
import com.example.notedatabase.data.model.NoteModel
import com.example.notedatabase.domain.entity.Note

fun Note.toModel() = NoteModel(
	id = id,
	title = title,
	description = description,
)

fun NoteModel.toEntity() = Note(
	id = id,
	title = title,
	description = description,
)

fun NoteModel.toDBEntity() = NoteDBEntity(
	id = id,
	title = title,
	description = description,
)

fun NoteDBEntity.toModel() = NoteModel(
	id = id,
	title = title,
	description = description,
)