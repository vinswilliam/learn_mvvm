package com.example.testmandiri.review.data

import android.util.Log
import androidx.paging.PagingSource
import com.example.testmandiri.data.Result
import retrofit2.HttpException
import java.io.IOException

private const val INDEX = 1

class ReviewPageDataSource(
    private val dataSource: ReviewRemoteDataSource,
    private val id: String
) : PagingSource<Int, Review>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> {
        val position = params.key ?: INDEX

        return try {
            val response = dataSource.fetchReview(position, id)

            Log.d("WILLIAM", "message ${response.message}")

            if (response.status == Result.Status.SUCCESS) {
                val repos = response.data?.results!!

                LoadResult.Page(
                    data = repos,
                    prevKey = if (position == INDEX) null else position - 1,
                    nextKey = if (repos.isEmpty()) null else position + 1
                )
            } else {
                Log.e("WILLIAM", "uups")
                LoadResult.Error(Exception(response.message))
            }
        } catch (exception: IOException) {
            Log.e("WILLIAM", "${exception.message}")
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            Log.e("WILLIAM", "${exception.message}")
            LoadResult.Error(exception)
        }
    }

}