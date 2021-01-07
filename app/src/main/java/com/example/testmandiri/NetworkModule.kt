package com.example.testmandiri

import com.example.testmandiri.api.MovieDBService
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun <T> createRetrofit(clazz: Class<T>): T {

    val httpLoggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }


    val okHttpClient: OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(httpLoggingInterceptor)
//        .addNetworkInterceptor(HttpLoggingInterceptor())
        .build()

    return Retrofit.Builder()
        .baseUrl(MovieDBService.ENDPOINT)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build().create(clazz)
}
