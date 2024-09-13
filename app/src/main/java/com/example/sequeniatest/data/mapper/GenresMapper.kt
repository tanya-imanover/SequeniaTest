package com.example.sequeniatest.data.mapper

import com.example.sequeniatest.domain.Film

class GenresMapper {

    fun getGenresFromFilmList(list: List<Film>): List<String>{
        val genres = list
            .flatMap { it.genres?.toList()?: listOf() }
            .toSet()
            .toList()

        return genres
    }
}