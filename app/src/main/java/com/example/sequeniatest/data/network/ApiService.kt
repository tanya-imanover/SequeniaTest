package com.example.sequeniatest.data.network

import com.example.sequeniatest.data.network.model.FilmDto
import com.example.sequeniatest.data.network.model.FilmListDto
import retrofit2.http.GET

interface ApiService {

    @GET("films.json")
    suspend fun loadMovies() : FilmListDto

}