package com.example.sequeniatest.di

import com.example.sequeniatest.presentation.FilmListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{

    viewModel<FilmListViewModel>{
        FilmListViewModel(
            getFilmListUseCase = get(),
            getGenresUseCase = get(),
            genreSelectedUseCase = get(),
            genreDeselectedUseCase = get(),
            loadDataUseCase = get()
        )
    }

}