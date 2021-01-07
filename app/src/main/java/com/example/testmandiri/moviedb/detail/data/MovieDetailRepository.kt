package com.example.testmandiri.moviedb.detail.data

import com.example.testmandiri.api.MovieDBService
import com.example.testmandiri.createRetrofit

class MovieDetailRepository {

    private val movieDetailRemoteDataSource: MovieDetailRemoteDataSource =
        MovieDetailRemoteDataSource(createRetrofit(MovieDBService::class.java))

    suspend fun getMovieDetail(id: String): MovieDetail {
        return MovieDetailPageDataSource(id, movieDetailRemoteDataSource).load()
    }

    suspend fun getVideo(id: String): List<VideoDB> {
        return MovieDetailPageDataSource(id, movieDetailRemoteDataSource).loadVideo()
    }

}