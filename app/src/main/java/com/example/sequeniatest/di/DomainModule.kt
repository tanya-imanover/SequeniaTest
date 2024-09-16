package com.example.sequeniatest.di

import com.example.sequeniatest.domain.GenreDeselectedUseCase
import com.example.sequeniatest.domain.GenreSelectedUseCase
import com.example.sequeniatest.domain.GetFilmListByGenre
import com.example.sequeniatest.domain.GetFilmListUseCase
import com.example.sequeniatest.domain.GetFilmUseCase
import com.example.sequeniatest.domain.GetGenresUseCase
import com.example.sequeniatest.domain.LoadDataUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetFilmListUseCase>{
        GetFilmListUseCase(repository = get())
    }

    factory<GetFilmUseCase>{
        GetFilmUseCase(repository = get())
    }

    factory<GetGenresUseCase>{
        GetGenresUseCase(repository = get())
    }

    factory<GetFilmListByGenre>{
        GetFilmListByGenre(repository = get())
    }

    factory<GenreDeselectedUseCase>{
        GenreDeselectedUseCase(repository = get())
    }

    factory<GenreSelectedUseCase>{
        GenreSelectedUseCase(repository = get())
    }

    factory<LoadDataUseCase>{
        LoadDataUseCase(repository = get())
    }



}