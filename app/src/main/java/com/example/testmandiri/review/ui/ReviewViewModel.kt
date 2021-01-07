package com.example.testmandiri.review.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.testmandiri.review.data.Review
import com.example.testmandiri.review.data.ReviewRepository
import kotlinx.coroutines.flow.Flow

class ReviewViewModel(
    private val repository: ReviewRepository
): ViewModel() {

    fun getReview(id: String): Flow<PagingData<Review>> {
        val result: Flow<PagingData<Review>> = repository.getReview(id)
            .cachedIn(viewModelScope)
        return result
    }
}