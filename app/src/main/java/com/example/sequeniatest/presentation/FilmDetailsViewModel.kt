package com.example.sequeniatest.presentation

import androidx.lifecycle.ViewModel
import com.example.sequeniatest.data.repository.FilmRepositoryImpl
import com.example.sequeniatest.domain.Film
import com.example.sequeniatest.domain.GetFilmUseCase

class FilmDetailsViewModel : ViewModel() {

    private val repository = FilmRepositoryImpl

    private val getFilmUseCase = GetFilmUseCase(repository)


    fun getFilm(id: Int): Film {
        return getFilmUseCase(id)
    }

    fun getYearGenresString(film: Film): String {
        val result = StringBuilder()
        result.append(film.year)
        film.genres?.forEach {
            result.append(", ")
            result.append(it)
        }
        return result.toString()
    }

    fun getRatingString(rating: Double): String {
        return rating.toString().substring(0, 3)
    }


}