package com.example.sequeniatest.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sequeniatest.data.mapper.FilmMapper
import com.example.sequeniatest.data.network.ApiFactory
import com.example.sequeniatest.data.mapper.GenresMapper
import com.example.sequeniatest.domain.Film
import com.example.sequeniatest.domain.FilmRepository
import com.example.sequeniatest.domain.Genre

object FilmRepositoryImpl : FilmRepository{

    private val retrofit = ApiFactory.apiService

    private var filmList = listOf<Film>()
    private var genres = listOf<Genre>()

    private val filmListLD = MutableLiveData<List<Film>>()
    private val genresLD = MutableLiveData<List<Genre>>()

    private val filmMapper = FilmMapper()
    private val genresMapper = GenresMapper()

    override fun getFilmList(): LiveData<List<Film>> {
        return filmListLD
    }

    override fun getFilmListByGenre(genre: String): LiveData<List<Film>> {
        filmListLD.value = filmList.filter { it.genres?.contains(genre) ?: false }
        return filmListLD
    }

    override fun getGenres(): LiveData<List<Genre>> {
        return genresLD
    }

    override fun getFilm(id: Int): Film {
        return  filmList.find {
            it.id == id
        } ?:throw RuntimeException("Element with id $id not found")
    }

    override fun genreSelected(genre: Genre) {
        genres.forEach { it.selected = it.genre == genre.genre }
        genresLD.value = genres
        getFilmListByGenre(genre.genre)
    }

    override fun genreDeselected() {
        genres.forEach { it.selected = false }
        genresLD.value = genres
        filmListLD.value = filmList
    }

    override suspend fun loadFilms() {
        try {
            val filmsDto = retrofit.loadMovies()
            filmList = filmMapper
                .filmListDtoToFilmListEntity(filmsDto)
                .sortedBy { it.localizedName }
            filmListLD.value = filmList
            genres = genresMapper.getGenresFromFilmList(filmList)
            genresLD.value = genres
        }catch (e: Exception){
            Log.d("FilmRepositoryImpl", e.message.toString())
        }
    }


}