package com.example.sequeniatest.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sequeniatest.data.mapper.FilmMapper
import com.example.sequeniatest.data.mapper.GenresMapper
import com.example.sequeniatest.data.network.ApiFactory
import com.example.sequeniatest.data.network.ApiService
import com.example.sequeniatest.domain.Film
import com.example.sequeniatest.domain.FilmRepository
import com.example.sequeniatest.domain.Genre

class FilmRepositoryImpl (private val apiService: ApiService): FilmRepository{

    private val _filmListLD = MutableLiveData<List<Film>>()
    val filmListLD: LiveData<List<Film>>
        get() = _filmListLD
    private val _genresLD = MutableLiveData<List<Genre>>()
    val genresLD: LiveData<List<Genre>>
        get() = _genresLD

    private val filmMapper = FilmMapper()
    private val genresMapper = GenresMapper()

    override fun getFilmList(): LiveData<List<Film>> {
        return filmListLD
    }

    override fun getFilmListByGenre(genre: String): LiveData<List<Film>> {
        _filmListLD.value = filmList.filter { it.genres?.contains(genre) ?: false }
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
        _genresLD.value = genres
        getFilmListByGenre(genre.genre)
    }

    override fun genreDeselected() {
        genres.forEach { it.selected = false }
        _genresLD.value = genres
        _filmListLD.value = filmList
    }

    override suspend fun loadFilms() {

            val filmsDto = apiService.loadMovies()
            filmList = filmMapper
                .filmListDtoToFilmListEntity(filmsDto)
                .sortedBy { it.localizedName }
            _filmListLD.value = filmList
            genres = genresMapper.getGenresFromFilmList(filmList)
            _genresLD.value = genres

    }

    companion object{
        private var filmList = listOf<Film>()
        private var genres = listOf<Genre>()
    }


}