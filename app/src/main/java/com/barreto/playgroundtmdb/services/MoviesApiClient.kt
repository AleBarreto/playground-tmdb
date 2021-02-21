package com.barreto.playgroundtmdb.services

import com.barreto.playgroundtmdb.BuildConfig
import com.barreto.playgroundtmdb.model.MovieResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiClient {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY_TMDB
    ): Response<MovieResult>

}