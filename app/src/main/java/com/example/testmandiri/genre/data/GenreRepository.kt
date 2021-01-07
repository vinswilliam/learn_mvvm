package com.example.testmandiri.genre.data

import com.example.testmandiri.api.MovieDBService
import com.example.testmandiri.createRetrofit
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@ExperimentalCoroutinesApi
class GenreRepository {
    private val genreRemoteDataSource: GenreRemoteDataSource =
        GenreRemoteDataSource(createRetrofit(MovieDBService::class.java))

    fun getGenreResultStream(): Flow<Genre> {

        return flow {
            GenrePageDataSource(genreRemoteDataSource).load().forEach {
                genre -> emit(genre)
            }

        }
    }
}