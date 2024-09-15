package com.example.sequeniatest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sequeniatest.data.repository.FilmRepositoryImpl
import com.example.sequeniatest.domain.Film
import com.example.sequeniatest.domain.GetFilmListUseCase
import com.example.sequeniatest.domain.GetFilmUseCase
import com.example.sequeniatest.domain.LoadDataUseCase
import kotlinx.coroutines.launch

class FilmDetailsViewModel : ViewModel() {

    private val repository = FilmRepositoryImpl

    private val getFilmUseCase = GetFilmUseCase(repository)
    private val getFilmListUseCase = GetFilmListUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val filmList = getFilmListUseCase()

    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }

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