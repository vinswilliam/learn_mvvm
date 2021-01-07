package com.example.testmandiri.genre.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.testmandiri.genre.data.Genre
import com.example.testmandiri.genre.data.GenreRepository
import kotlinx.coroutines.flow.Flow

class GenreViewModel(private val repository: GenreRepository) : ViewModel() {

    fun fetchGenre(): Flow<Genre> {
        val result: Flow<Genre> = repository.getGenreResultStream()
        return result
    }

}