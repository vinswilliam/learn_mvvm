package com.example.testmandiri

import androidx.lifecycle.ViewModelProvider
import com.example.testmandiri.genre.data.GenreRepository
import com.example.testmandiri.moviedb.detail.data.MovieDetailRepository
import com.example.testmandiri.moviedb.detail.ui.MovieDetailViewModel
import com.example.testmandiri.moviedb.list.data.MovieDBRepository
import com.example.testmandiri.review.data.ReviewRepository

object Injection {
    private fun provideGenreRepository(): GenreRepository {
        return GenreRepository()
    }

    fun provideGenreViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelFactory(genreRepository = provideGenreRepository())
    }

    private fun provideMovieDBRepository(): MovieDBRepository {
        return MovieDBRepository()
    }

    fun provideMovieViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelFactory(movieDBRepository = provideMovieDBRepository())
    }

    private fun provideMovieDetailRepository(): MovieDetailRepository {
        return MovieDetailRepository()
    }

    fun provideMovieDetailViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelFactory(movieDetailRepository = provideMovieDetailRepository())
    }

    private fun provideReviewRepository(): ReviewRepository {
        return ReviewRepository()
    }

    fun provideReviewViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelFactory(reviewRepository = provideReviewRepository())
    }
}