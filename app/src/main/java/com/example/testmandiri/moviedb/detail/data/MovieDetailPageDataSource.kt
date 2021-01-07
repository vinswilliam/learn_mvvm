package com.example.testmandiri.moviedb.detail.data

class MovieDetailPageDataSource(
    private val id: String,
    private val dataSource: MovieDetailRemoteDataSource
) {
    suspend fun load(): MovieDetail {
        val response = dataSource.fetchMovieDetail(id)
        return response.data!!
    }

    suspend fun loadVideo(): List<VideoDB> {
        val response = dataSource.getVideoDetail(id)
        return response.data?.results!!
    }
}