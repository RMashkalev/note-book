package com.example.notedatabase.domain.entity

import android.graphics.Color

data class Note(
	val id: Long,
	val title: String,
	val description: String,
	val firstColor: Long,
	val secondColor: Long,
)