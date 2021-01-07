package com.example.testmandiri.moviedb.list.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.testmandiri.moviedb.list.data.MovieDB
import com.example.testmandiri.moviedb.list.data.MovieDBRepository
import kotlinx.coroutines.flow.Flow

class MovieViewModel(
    private val repository: MovieDBRepository,
) : ViewModel() {

    fun getMovie(ids: Array<String>): Flow<PagingData<MovieDB>> {
        val result: Flow<PagingData<MovieDB>> = repository.getMovieStream(ids)
            .cachedIn(viewModelScope)

        return result
    }

}