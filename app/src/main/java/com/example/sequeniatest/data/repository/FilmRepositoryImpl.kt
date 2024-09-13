package com.example.sequeniatest.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sequeniatest.data.mapper.FilmMapper
import com.example.sequeniatest.data.network.ApiFactory
import com.example.sequeniatest.data.mapper.GenresMapper
import com.example.sequeniatest.domain.Film
import com.example.sequeniatest.domain.FilmRepository

class FilmRepositoryImpl : FilmRepository{

    private val retrofit = ApiFactory.apiService

    private var filmList = listOf<Film>()
    private val filmListLD = MutableLiveData<List<Film>>()
    private val genresLD = MutableLiveData<List<String>>()

    private val filmMapper = FilmMapper()
    private val genresMapper = GenresMapper()

    override fun getFilmList(): LiveData<List<Film>> {
        return filmListLD
    }

    override fun getFilmListByGenre(genre: String): LiveData<List<Film>> {
        filmListLD.value = filmList.filter { it.genres?.contains(genre) ?: false }
        return filmListLD
    }

    override fun getGenres(): LiveData<List<String>> {
        return genresLD
    }

    override fun getFilm(id: Int): Film {
        return filmListLD.value?.find {
            it.id == id
        } ?:throw RuntimeException("Element with id $id not found")
    }

    override suspend fun loadFilms() {
        try {
            val filmsDto = retrofit.loadMovies()
            Log.d("FilmRepositoryImpl", filmsDto.toString())
            filmList = filmMapper.filmListDtoToFilmListEntity(filmsDto)
            filmListLD.value = filmList
            genresLD.value = genresMapper.getGenresFromFilmList(filmList)
        }catch (e: Exception){
            Log.d("FilmRepositoryImpl", e.message.toString())
        }
    }


}