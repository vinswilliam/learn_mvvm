package com.example.testmandiri.moviedb.detail.data

import com.example.testmandiri.api.BaseDataSource
import com.example.testmandiri.api.MovieDBService

class MovieDetailRemoteDataSource(private val service: MovieDBService) : BaseDataSource() {

    suspend fun fetchMovieDetail(id: String) = getResult { service.movieDetail(movieId = id) }

    suspend fun getVideoDetail(id: String) = getResult { service.getVideos(id) }
}