package com.example.notedatabase.data.model

import android.graphics.Color

data class NoteModel(
	val id: Long,
	val title: String,
	val description: String,
	val firstColor: Long,
	val secondColor: Long,
)