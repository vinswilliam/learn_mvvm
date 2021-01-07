package com.example.testmandiri.moviedb.list.data

import com.example.testmandiri.api.BaseDataSource
import com.example.testmandiri.api.MovieDBService

class MovieDBRemoteDataSource(private val service: MovieDBService) : BaseDataSource() {

    suspend fun fetchMovie(page: Int, ids: Array<String>) = getResult {
        service.dicoverMovieByGenre(genres = ids, page = page)
    }

}