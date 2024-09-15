package com.example.sequeniatest.domain

class GenreSelectedUseCase (private val repository: FilmRepository){
    operator fun invoke(genre: Genre) = repository.genreSelected(genre)
}