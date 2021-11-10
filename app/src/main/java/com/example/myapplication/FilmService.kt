package com.example.myapplication

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmService {


    @Headers(
        "Content-type: application/json"
    )

    @GET("search/movie")

    suspend fun datas(
        @Query("language",encoded =false) language: String,
        @Query("query", encoded = false) query: String,
        @Query("api_key", encoded = false) apikey: String
    ) : Response<FilmResponse>


    @Headers(
        "Content-type: application/json"
    )
    @GET("/trending/{media_type}/{time_window}")
    suspend fun datasTrend(
        @Path("media_type") mediaType: String,
        @Path("time_window") timeWindow: String,
        @Query("api_key", encoded = false) apikey: String,

    ) : Response<FilmResponse>
}