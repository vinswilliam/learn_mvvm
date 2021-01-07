package com.example.testmandiri

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testmandiri.genre.data.GenreRepository
import com.example.testmandiri.genre.ui.GenreViewModel
import com.example.testmandiri.moviedb.detail.data.MovieDetailRepository
import com.example.testmandiri.moviedb.detail.ui.MovieDetailViewModel
import com.example.testmandiri.moviedb.list.data.MovieDBRepository
import com.example.testmandiri.moviedb.list.ui.MovieViewModel
import com.example.testmandiri.review.data.ReviewRepository
import com.example.testmandiri.review.ui.ReviewViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val genreRepository: GenreRepository? = null,
    private val movieDBRepository: MovieDBRepository? = null,
    private val movieDetailRepository: MovieDetailRepository? = null,
    private val reviewRepository: ReviewRepository? = null
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GenreViewModel::class.java)) {
            return GenreViewModel(genreRepository!!) as T
        } else if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(movieDBRepository!!) as T
        } else if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            return MovieDetailViewModel(movieDetailRepository!!) as T
        } else if (modelClass.isAssignableFrom(ReviewViewModel::class.java)) {
            return ReviewViewModel(reviewRepository!!) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}