package com.example.testmandiri.genre.data

import com.example.testmandiri.data.Result


class GenrePageDataSource(
    private val dataSource: GenreRemoteDataSource
) {

    suspend fun load() : List<Genre> {
        val response = dataSource.fetchSets()

        if (response.status == Result.Status.SUCCESS) {
            return response.data?.genres!!
        } else {
            return listOf()
        }
    }
}