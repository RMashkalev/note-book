package com.example.note_book.di

import com.example.note_book.navigation.AppNavGraph
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module

@Module
@ComponentScan("com.example.note_book")
class MainModule