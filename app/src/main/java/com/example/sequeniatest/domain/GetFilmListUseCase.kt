package com.example.sequeniatest.domain

class GetFilmListUseCase (private val repository: FilmRepository){
    operator fun invoke() = repository.getFilmList()
}