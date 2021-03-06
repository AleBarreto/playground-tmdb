package com.barreto.playgroundtmdb.services

import com.barreto.playgroundtmdb.BuildConfig
import com.barreto.playgroundtmdb.model.CreditsResult
import com.barreto.playgroundtmdb.model.MovieResult
import com.barreto.playgroundtmdb.model.MovieResultDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiClient {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY_TMDB,
        @Query("language")
        language: String = "pt-BR"
    ): Response<MovieResult>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY_TMDB,
        @Query("language")
        language: String = "pt-BR"
    ): Response<MovieResult>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY_TMDB,
        @Query("language")
        language: String = "pt-BR"
    ): Response<MovieResult>

    @GET("movie/{id}/credits")
    suspend fun getCreditsByMovieId(
        @Path("id")
        idMovie: Long,
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY_TMDB,
        @Query("language")
        language: String = "pt-BR"
    ): Response<CreditsResult>

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id")
        idMovie: Long,
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY_TMDB,
        @Query("language")
        language: String = "pt-BR"
    ): Response<MovieResultDetail>

}