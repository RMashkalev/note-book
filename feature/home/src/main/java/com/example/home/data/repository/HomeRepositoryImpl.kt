package com.example.home.data.repository

import com.example.home.data.datasource.HomeDataSourcePref
import com.example.home.data.mapper.toEntity
import com.example.home.domain.entity.Note
import com.example.home.domain.repository.HomeRepository

class HomeRepositoryImpl(
	private val dataSourcePref: HomeDataSourcePref
) : HomeRepository {

	override fun getNotes(): List<Note> =
		dataSourcePref.getNotes().map { it.toEntity() }
}