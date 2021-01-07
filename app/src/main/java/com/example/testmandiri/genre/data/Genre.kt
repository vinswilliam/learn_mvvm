package com.example.testmandiri.genre.data

data class Genre(
    val id: Int,
    val name: String)

data class GenreResultResponse(
    val genres: List<Genre>
)