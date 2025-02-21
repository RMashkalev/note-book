package com.example.note_book.app

import android.app.Application
import com.example.home.di.HomeModule
import com.example.note_book.di.MainModule
import com.example.notedatabase.di.NoteDatabaseModule
import com.example.notedatabase.di.roomDatabase
import com.example.notedetail.di.NoteDetailModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.ksp.generated.module

class App : Application() {

	override fun onCreate() {
		super.onCreate()

		startKoin {
			androidLogger()
			androidContext(this@App)
			modules(
//				MainModule().module,
				roomDatabase,
				HomeModule().module,
				NoteDetailModule().module,
				NoteDatabaseModule().module,

			)
		}
	}
}