package com.barreto.playgroundtmdb.repository

import com.barreto.playgroundtmdb.model.Cast
import com.barreto.playgroundtmdb.model.Movie
import com.barreto.playgroundtmdb.model.MovieResultDetail
import com.barreto.playgroundtmdb.services.Result

interface RepositoryContract {

    suspend fun getPopularMovies(): Result<List<Movie>>
    suspend fun getTopRatedMovies(): Result<List<Movie>>
    suspend fun getNowPlayingMovies(): Result<List<Movie>>
    suspend fun getCreditsByMovieId(id: Long): Result<List<Cast>>
    suspend fun getMovieDetail(id: Long): Result<MovieResultDetail>

}