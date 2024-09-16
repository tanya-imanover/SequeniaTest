package com.example.sequeniatest.domain

class GetFilmListByGenre (private val repository: FilmRepository){
    operator fun invoke(genre: String) = repository.getFilmListByGenre(genre)
}