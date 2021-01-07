package com.example.testmandiri.genre.data

import com.example.testmandiri.api.BaseDataSource
import com.example.testmandiri.api.MovieDBService

class GenreRemoteDataSource(private val service: MovieDBService) : BaseDataSource() {

    suspend fun fetchSets() = getResult { service.getGenres() }
}