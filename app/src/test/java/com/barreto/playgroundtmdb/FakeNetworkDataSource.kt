package com.barreto.playgroundtmdb

import com.barreto.playgroundtmdb.model.Cast
import com.barreto.playgroundtmdb.model.Movie
import com.barreto.playgroundtmdb.model.MovieResultDetail
import com.barreto.playgroundtmdb.repository.MovieDataSource

class FakeNetworkDataSource(var movies: MutableList<Movie>? = mutableListOf()) :
    MovieDataSource {

    override suspend fun getPopularMovies(): List<Movie> {
        movies?.let {
            return it
        }
        return emptyList()
    }

    override suspend fun getTopRatedMovies(): List<Movie> {
        TODO("Not yet implemented")
    }

    override suspend fun getNowPlayingMovies(): List<Movie> {
        TODO("Not yet implemented")
    }

    override suspend fun getCreditsByMovieId(id: Long): List<Cast> {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieDetail(id: Long): MovieResultDetail {
        TODO("Not yet implemented")
    }


}