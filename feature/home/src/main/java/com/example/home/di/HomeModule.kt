package com.example.home.di

import com.example.home.data.datasource.HomeDataSourcePref
import com.example.home.data.datasource.HomeDataSourcePrefImpl
import com.example.home.data.pref.HomeSharedPref
import com.example.home.data.repository.HomeRepositoryImpl
import com.example.home.domain.repository.HomeRepository
import com.example.home.domain.usecase.GetNotesUseCase
import com.example.home.presentation.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module

@Module
@ComponentScan("com.example.home")
class HomeModule