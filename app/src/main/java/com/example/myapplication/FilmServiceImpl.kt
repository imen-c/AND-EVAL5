package com.example.myapplication

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FilmServiceImpl {
    val baseUrl = "https://api.themoviedb.org/3/"
    companion object{
        val APIToken = "b2168bae3a2c67509eb6b97572f521c2"
        val language = "en_US"
        val mediaType = "movie"
        val timWindow = "day"

    }
    fun getRetrofit() : Retrofit {
        val okBuilder = OkHttpClient().newBuilder().apply {
            connectTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
            callTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
            readTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
            writeTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
        }
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okBuilder.build())
            .build()
    }
    suspend fun getMovies(language: String, query: String, apikey: String) : Response<FilmResponse> =
        getRetrofit().create(FilmService::class.java).datas(language, query,apikey)
    suspend fun getTrend(mediaType: String,timeWindow :String,apiKey: String) :Response<FilmResponse> =
        getRetrofit().create(FilmService::class.java).datasTrend(mediaType,timeWindow,apiKey)
}