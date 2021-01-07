package com.example.testmandiri.api

import com.google.gson.annotations.SerializedName

data class ResultsResponse<T>(

  @SerializedName("page")
  val page: Int,
  @SerializedName("total_results")
  val totalResults: Int,
  @SerializedName("total_pages")
  val totalPages: Int,
  @SerializedName("results")
  var results: List<T>
)