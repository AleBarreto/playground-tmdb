package com.barreto.playgroundtmdb.repository

import com.barreto.playgroundtmdb.model.Movie
import com.barreto.playgroundtmdb.services.DataResource

interface RepositoryContract {

    suspend fun getPopularMovies(): DataResource<List<Movie>>

}