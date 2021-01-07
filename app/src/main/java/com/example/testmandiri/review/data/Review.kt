package com.example.testmandiri.review.data

import com.google.gson.annotations.SerializedName

data class Review(
    val author: String,
    @SerializedName("author_details")
    val authorDetails: AuthorDetail,
    val content: String,
    @SerializedName("created_at")
    val createdAt: String,
    val id: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val url: String,
)

data class AuthorDetail(
    val name: String,
    val username: String,
    val avatar_path: String?,
    val rating: Int?
)