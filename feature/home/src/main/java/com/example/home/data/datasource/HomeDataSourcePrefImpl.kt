package com.example.home.data.datasource

import com.example.home.data.model.NoteModel
import com.example.home.data.pref.HomeSharedPref
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam

interface HomeDataSourcePref {

	fun getNotes() : List<NoteModel>
}

@Factory
class HomeDataSourcePrefImpl(
	private val pref: HomeSharedPref,
) : HomeDataSourcePref {

	override fun getNotes(): List<NoteModel> =
		pref.getNotes()

}