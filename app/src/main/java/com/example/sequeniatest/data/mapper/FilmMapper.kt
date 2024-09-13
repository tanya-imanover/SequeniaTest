package com.example.sequeniatest.data.mapper

import com.example.sequeniatest.data.network.model.FilmDto
import com.example.sequeniatest.data.network.model.FilmListDto
import com.example.sequeniatest.domain.Film

class FilmMapper {

    private fun filmDtoToFilmEntity(dto: FilmDto): Film {
        return Film(
            id = dto.id,
            localizedName = dto.localizedName,
            name = dto.name,
            year = dto.year,
            rating = dto.rating,
            imageUrl = dto.imageUrl,
            description = dto.description,
            genres = dto.genres
        )
    }

    fun filmListDtoToFilmListEntity(dto: FilmListDto): List<Film>{
        return dto.films.map{filmDtoToFilmEntity(it)}
    }
}