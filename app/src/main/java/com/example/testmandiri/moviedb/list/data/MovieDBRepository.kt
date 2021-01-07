package com.example.testmandiri.moviedb.list.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.testmandiri.api.MovieDBService
import com.example.testmandiri.createRetrofit
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class MovieDBRepository {

    private val movieDBRemoteDataSource: MovieDBRemoteDataSource =
        MovieDBRemoteDataSource((createRetrofit(MovieDBService::class.java)))

    fun getMovieStream(ids: Array<String>): Flow<PagingData<MovieDB>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MovieDBPageDataSource(movieDBRemoteDataSource, ids) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }
}