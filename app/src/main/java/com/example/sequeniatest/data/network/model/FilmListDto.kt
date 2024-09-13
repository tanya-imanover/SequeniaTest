package com.example.sequeniatest.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FilmListDto(
    @SerializedName("films")
    val films: List<FilmDto>
)