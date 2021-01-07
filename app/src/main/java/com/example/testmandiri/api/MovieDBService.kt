package com.example.testmandiri.api

import com.example.testmandiri.genre.data.GenreResultResponse
import com.example.testmandiri.moviedb.detail.data.MovieDetail
import com.example.testmandiri.moviedb.list.data.MovieDB
import com.example.testmandiri.review.data.Review
import com.example.testmandiri.moviedb.detail.data.VideoDB
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDBService {

    companion object {
        const val ENDPOINT = "https://api.themoviedb.org/3/"
        const val API_KEY = "6e227a7496ad1d1a17863c73da29597a"
    }

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US"
    ): Response<GenreResultResponse>

    @GET("discover/movie")
    suspend fun dicoverMovieByGenre(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
        @Query("with_genres") genres: Array<String>,
        @Query("page") page: Int,
    ): Response<ResultsResponse<MovieDB>>

    @GET("movie/{movie_id}")
    suspend fun movieDetail(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US"
    ): Response<MovieDetail>

    @GET("movie/{movie_id}/reviews")
    suspend fun getReviews(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): Response<ResultsResponse<Review>>

    @GET("movie/{movie_id}/videos")
    suspend fun getVideos(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
    ): Response<ResultsResponse<VideoDB>>
}