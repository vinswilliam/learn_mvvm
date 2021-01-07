package com.example.testmandiri.moviedb.detail.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testmandiri.moviedb.detail.data.MovieDetail
import com.example.testmandiri.moviedb.detail.data.MovieDetailRepository
import com.example.testmandiri.moviedb.detail.data.VideoDB
import java.util.*

class MovieDetailViewModel(private val repository: MovieDetailRepository) : ViewModel() {

    suspend fun getMovieDetail(id: String): MutableLiveData<MovieDetail> {
        val movie = MutableLiveData<MovieDetail>()
        movie.value = repository.getMovieDetail(id)
        return movie
    }

    suspend fun getVideo(id: String): MutableLiveData<VideoDB?> {
        val video = MutableLiveData<VideoDB?>()
        video.value = repository.getVideo(id).firstOrNull() {
            it.site.toLowerCase(Locale.ROOT) == "youtube" && it.name.toLowerCase(Locale.ROOT) == "trailer"
        }
        return video
    }
}