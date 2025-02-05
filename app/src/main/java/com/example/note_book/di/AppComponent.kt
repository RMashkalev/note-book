package com.example.note_book.di

import com.example.note_book.presentation.MainActivity
import dagger.Component

@Component(modules = [AppModule::class, HomeModule::class])
interface AppComponent {

	fun inject(mainActivity: MainActivity)
}