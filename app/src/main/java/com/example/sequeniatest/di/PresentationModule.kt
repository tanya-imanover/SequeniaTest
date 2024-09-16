package com.example.sequeniatest.di

import com.example.sequeniatest.databinding.FragmentFilmDetailsBinding
import com.example.sequeniatest.domain.GetFilmUseCase
import com.example.sequeniatest.presentation.FilmDetailsViewModel
import com.example.sequeniatest.presentation.FilmListViewModel
import com.example.sequeniatest.presentation.adapter.FilmsAdapter
import com.example.sequeniatest.presentation.adapter.GenresAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module{

    viewModel<FilmListViewModel>{
        FilmListViewModel(
            getFilmListUseCase = get(),
            getGenresUseCase = get(),
            genreSelectedUseCase = get(),
            genreDeselectedUseCase = get(),
            loadDataUseCase = get()
        )
    }

    viewModel<FilmDetailsViewModel>{
        FilmDetailsViewModel(
            getFilmUseCase = get()
        )
    }

    factory<GenresAdapter>{
        GenresAdapter()
    }

    factory <FilmsAdapter>{
        FilmsAdapter()
    }


}