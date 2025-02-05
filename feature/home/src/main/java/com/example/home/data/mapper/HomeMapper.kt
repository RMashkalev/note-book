package com.example.home.data.mapper

import com.example.home.data.model.NoteModel
import com.example.home.domain.entity.Note

internal fun NoteModel.toEntity() =
	Note(
		text = text
	)