package com.example.sequeniatest.data.mapper


import com.example.sequeniatest.domain.Film
import com.example.sequeniatest.domain.Genre

class GenresMapper {

    fun getGenresFromFilmList(list: List<Film>): List<Genre>{
        val genresStringList = list
            .flatMap { it.genres?.toList()?: listOf() }
            .toSet()
            .toList()
        val genres = genresStringList.map { Genre(it, false) }

        return genres
    }
}