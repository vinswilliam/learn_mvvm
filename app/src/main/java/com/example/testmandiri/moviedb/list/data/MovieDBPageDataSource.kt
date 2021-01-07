package com.example.testmandiri.moviedb.list.data

import android.util.Log
import androidx.paging.PagingSource
import retrofit2.HttpException
import java.io.IOException

import com.example.testmandiri.data.Result

private const val MOVIEDB_STARTING_PAGE_INDEX = 1

class MovieDBPageDataSource(
    private val dataSource: MovieDBRemoteDataSource,
    private val ids: Array<String>
) : PagingSource<Int, MovieDB>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDB> {
        val position = params.key ?: MOVIEDB_STARTING_PAGE_INDEX

        return try {
            val response = dataSource.fetchMovie(position, ids)

            if (response.status == Result.Status.SUCCESS) {
                val repos = response.data?.results!!

                LoadResult.Page(
                    data = repos,
                    prevKey = if (position == MOVIEDB_STARTING_PAGE_INDEX) null else position - 1,
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