package com.example.notedatabase.di

import android.app.Application
import androidx.room.Room
import com.example.notedatabase.data.database.NoteDatabase
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module
import org.koin.android.ext.koin.androidApplication

@Module
@ComponentScan("com.example.notedatabase")
class NoteDatabaseModule

val roomDatabase = module {

	single { Room.databaseBuilder(androidApplication(), NoteDatabase::class.java, "NoteDatabase").build() }

	single { get<NoteDatabase>().noteDatabaseDAO() }
}