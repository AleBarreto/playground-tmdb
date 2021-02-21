package com.barreto.playgroundtmdb.repository

import com.barreto.playgroundtmdb.model.Movie
import com.barreto.playgroundtmdb.services.DataResource
import com.barreto.playgroundtmdb.services.MoviesApiClient

class NetworkDataSource(private val webApiClient: MoviesApiClient) : RepositoryContract {

    override suspend fun getPopularMovies(): DataResource<List<Movie>> {
        val listResponse: List<Movie> = emptyList()
        val popularMovies = webApiClient.getPopularMovies()

        if (!popularMovies.isSuccessful) {
            return DataResource.Error("Error TODO", listResponse)
        } else {
            popularMovies.body().let {
                return if (it != null) {
                    DataResource.Success(it.results)
                } else {
                    DataResource.Error("Error TODO", listResponse)
                }
            }
        }
    }

    override suspend fun getTopRatedMovies(): DataResource<List<Movie>> {
        val listResponse: List<Movie> = emptyList()
        val popularMovies = webApiClient.getTopRatedMovies()

        if (!popularMovies.isSuccessful) {
            return DataResource.Error("Error TODO", listResponse)
        } else {
            popularMovies.body().let {
                return if (it != null) {
                    DataResource.Success(it.results)
                } else {
                    DataResource.Error("Error TODO", listResponse)
                }
            }
        }

    }

    override suspend fun getNowPlayingMovies(): DataResource<List<Movie>> {
        val listResponse: List<Movie> = emptyList()
        val popularMovies = webApiClient.getNowPlayingMovies()

        if (!popularMovies.isSuccessful) {
            return DataResource.Error("Error TODO", listResponse)
        } else {
            popularMovies.body().let {
                return if (it != null) {
                    DataResource.Success(it.results)
                } else {
                    DataResource.Error("Error TODO", listResponse)
                }
            }
        }
    }

}