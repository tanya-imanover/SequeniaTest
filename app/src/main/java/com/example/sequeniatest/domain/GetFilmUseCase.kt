package com.example.sequeniatest.domain

class GetFilmUseCase (private val repository: FilmRepository){
    operator fun invoke(id: Int) = repository.getFilm(id)
}