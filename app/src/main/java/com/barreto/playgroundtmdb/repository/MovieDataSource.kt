package com.barreto.playgroundtmdb.repository

import com.barreto.playgroundtmdb.model.Cast
import com.barreto.playgroundtmdb.model.Movie
import com.barreto.playgroundtmdb.model.MovieResultDetail

interface MovieDataSource {

    suspend fun getPopularMovies(): List<Movie>
    suspend fun getTopRatedMovies(): List<Movie>
    suspend fun getNowPlayingMovies(): List<Movie>
    suspend fun getCreditsByMovieId(id: Long): List<Cast>
    suspend fun getMovieDetail(id: Long): MovieResultDetail

}