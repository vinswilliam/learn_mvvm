package com.example.testmandiri.review.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.testmandiri.api.MovieDBService
import com.example.testmandiri.createRetrofit
import kotlinx.coroutines.flow.Flow

class ReviewRepository {

    private val reviewRemoteDataSource: ReviewRemoteDataSource =
        ReviewRemoteDataSource((createRetrofit(MovieDBService::class.java)))

    fun getReview(id: String): Flow<PagingData<Review>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ReviewPageDataSource(reviewRemoteDataSource, id) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }
}