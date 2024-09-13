package com.example.sequeniatest.domain

class GetGenresCase (private val repository: FilmRepository){
    operator fun invoke() = repository.getGenres()
}