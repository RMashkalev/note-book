package com.example.home.data.datasource

import com.example.home.data.model.NoteModel
import com.example.home.data.pref.HomeSharedPref

interface HomeDataSourcePref {

	fun getNotes() : List<NoteModel>
}

class HomeDataSourcePrefImpl(
	private val pref: HomeSharedPref,
) : HomeDataSourcePref {

	override fun getNotes(): List<NoteModel> =
		pref.getNotes()

}