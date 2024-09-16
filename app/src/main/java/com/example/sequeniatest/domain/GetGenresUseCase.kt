package com.example.sequeniatest.domain

class GetGenresUseCase (private val repository: FilmRepository){
    operator fun invoke() = repository.getGenres()
}