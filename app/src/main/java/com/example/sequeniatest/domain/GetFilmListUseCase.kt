package com.example.sequeniatest.domain

class GetFilmListUseCase (private val repository: FilmRepository){
    suspend operator fun invoke() = repository.getFilmList()
}