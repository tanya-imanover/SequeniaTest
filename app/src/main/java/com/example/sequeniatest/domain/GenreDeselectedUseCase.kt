package com.example.sequeniatest.domain

class GenreDeselectedUseCase (private val repository: FilmRepository){
    operator fun invoke() = repository.genreDeselected()
}