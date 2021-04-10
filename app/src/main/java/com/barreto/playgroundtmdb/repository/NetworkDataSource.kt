package com.barreto.playgroundtmdb.repository

import com.barreto.playgroundtmdb.model.Cast
import com.barreto.playgroundtmdb.model.Movie
import com.barreto.playgroundtmdb.model.MovieResult
import com.barreto.playgroundtmdb.model.MovieResultDetail
import com.barreto.playgroundtmdb.services.MoviesApiClient
import retrofit2.Response

class NetworkDataSource(private val webApiClient: MoviesApiClient) : MovieDataSource {

    override suspend fun getPopularMovies(): List<Movie> {
        return prepareData(webApiClient.getPopularMovies())
    }

    override suspend fun getTopRatedMovies(): List<Movie> {
        return prepareData(webApiClient.getTopRatedMovies())
    }

    override suspend fun getNowPlayingMovies(): List<Movie> {
        return prepareData(webApiClient.getNowPlayingMovies())
    }

    override suspend fun getCreditsByMovieId(id: Long): List<Cast> {
        val response = webApiClient.getCreditsByMovieId(id)
        response.body()?.let {
            return it.cast
        }
        return emptyList()
    }

    override suspend fun getMovieDetail(id: Long): MovieResultDetail {
        val response = webApiClient.getMovieDetail(id)
        response.body()?.let {
            return it
        }
        return MovieResultDetail(emptyList(), emptyList())
    }

    private fun prepareData(response: Response<MovieResult>): List<Movie> {
        if (response.isSuccessful && response.body() != null) {
            response.body()?.let {
                return it.results
            }
            return emptyList()
        } else {
            return emptyList()
        }
    }


}