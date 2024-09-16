package com.example.sequeniatest.domain

class LoadDataUseCase(private val repository: FilmRepository){
    suspend operator fun invoke() = repository.loadFilms()
}