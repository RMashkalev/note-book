package com.example.note_book.app

import android.app.Application
import com.example.note_book.di.AppComponent
import com.example.note_book.di.DaggerAppComponent
import com.example.note_book.di.HomeModule

class App : Application() {

	lateinit var appComponent: AppComponent

	override fun onCreate() {
		super.onCreate()

		appComponent = DaggerAppComponent
			.builder()
			.homeModule(HomeModule(context = this))
			.build()
	}
}