package com.example.sequeniatest.domain

import androidx.lifecycle.LiveData

interface FilmRepository {

    fun getFilmList(): LiveData<List<Film>>

    fun getFilmListByGenre(genre: String): LiveData<List<Film>>

    fun getGenres(): LiveData<List<String>>

    fun getFilm(id: Int): Film

    suspend fun  loadFilms()
}