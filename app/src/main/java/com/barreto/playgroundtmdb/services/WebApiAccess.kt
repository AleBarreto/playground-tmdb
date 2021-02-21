package com.barreto.playgroundtmdb.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WebApiAccess {
    val moviesApi: MoviesApiClient by lazy {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return@lazy retrofit.create(MoviesApiClient::class.java)
    }
}