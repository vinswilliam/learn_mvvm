package com.example.testmandiri.review.data

import com.example.testmandiri.api.BaseDataSource
import com.example.testmandiri.api.MovieDBService

class ReviewRemoteDataSource(private val service: MovieDBService) : BaseDataSource() {

    suspend fun fetchReview(page: Int, id: String) = getResult {
        service.getReviews(page = page, movieId = id)
    }
}