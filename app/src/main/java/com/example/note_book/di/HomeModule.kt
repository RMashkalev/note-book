package com.example.note_book.di

import android.content.Context
import com.example.home.data.datasource.HomeDataSourcePref
import com.example.home.data.datasource.HomeDataSourcePrefImpl
import com.example.home.data.pref.HomeSharedPref
import com.example.home.data.repository.HomeRepositoryImpl
import com.example.home.domain.repository.HomeRepository
import com.example.home.domain.usecase.GetNotesUseCase
import com.example.home.presentation.HomeViewModel
import dagger.Module
import dagger.Provides

@Module
class HomeModule(val context: Context) {

	@Provides
	fun provideContext(): Context {
		return context
	}

	@Provides
	fun provideHomeSharedPref(context: Context): HomeSharedPref {
		return HomeSharedPref(context)
	}

	@Provides
	fun provideHomeDataSource(pref: HomeSharedPref): HomeDataSourcePref {
		return HomeDataSourcePrefImpl(pref)
	}

	@Provides
	fun provideHomeRepository(dataSource: HomeDataSourcePref): HomeRepository {
		return HomeRepositoryImpl(dataSource)
	}

	@Provides
	fun provideGetNotesUseCase(repository: HomeRepository): GetNotesUseCase {
		return GetNotesUseCase(repository)
	}

	@Provides
	fun provideHomeViewModel(getNotesUseCase: GetNotesUseCase): HomeViewModel {
		return HomeViewModel(getNotesUseCase)
	}
}