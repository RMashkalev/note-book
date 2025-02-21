package com.example.notedatabase.di

import androidx.room.Room
import com.example.notedatabase.data.database.NoteDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module

@Module
@ComponentScan("com.example.notedatabase")
class NoteDatabaseModule

val roomDatabase = module {

	single { Room
		.databaseBuilder(androidApplication(), NoteDatabase::class.java, "NoteDatabase")
		.build()
	}

	single { get<NoteDatabase>().noteDatabaseDAO() }
}