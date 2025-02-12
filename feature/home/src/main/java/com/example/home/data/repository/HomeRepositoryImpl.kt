package com.example.home.data.repository

import com.example.home.data.datasource.HomeDataSourcePref
import com.example.home.data.mapper.toEntity
import com.example.home.domain.entity.Note
import com.example.home.domain.repository.HomeRepository
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam

@Factory
class HomeRepositoryImpl(
	private val dataSourcePref: HomeDataSourcePref
) : HomeRepository {

	override fun getNotes(): List<Note> =
		dataSourcePref.getNotes().map { it.toEntity() }
}