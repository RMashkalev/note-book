package com.example.home.data.pref

import android.content.Context
import com.example.home.data.model.NoteModel
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.Property

@Factory
class HomeSharedPref(
	context: Context,
) {

	companion object {

		const val HOME_SHARED_PREF = "HOME_SHARED_PREF"
	}

	private val sharedPreferences = context.getSharedPreferences(HOME_SHARED_PREF, Context.MODE_PRIVATE)

	fun getNotes(): List<NoteModel> {
		return listOf(
			NoteModel("Example1"),
			NoteModel("Example2"),
			NoteModel("Example3"),
			NoteModel("Example4"),
			NoteModel("Example5"),
		)
	}
}