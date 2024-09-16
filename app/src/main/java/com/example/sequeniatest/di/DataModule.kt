package com.example.sequeniatest.di

import com.example.sequeniatest.data.repository.FilmRepositoryImpl
import com.example.sequeniatest.domain.FilmRepository
import org.koin.dsl.module

val dataModule = module {

    single<FilmRepository>{
        FilmRepositoryImpl
    }
}