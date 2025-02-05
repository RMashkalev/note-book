package com.example.home.domain.repository

import com.example.home.domain.entity.Note

interface HomeRepository {

	fun getNotes() : List<Note>
}