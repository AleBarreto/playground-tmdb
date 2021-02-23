package com.barreto.playgroundtmdb.repository

import com.barreto.playgroundtmdb.model.Cast
import com.barreto.playgroundtmdb.model.CreditsResult
import com.barreto.playgroundtmdb.model.Movie
import com.barreto.playgroundtmdb.model.MovieResult
import com.barreto.playgroundtmdb.services.DataResource
import com.barreto.playgroundtmdb.services.MoviesApiClient
import retrofit2.Response

@Suppress("UNCHECKED_CAST")
class NetworkDataSource(private val webApiClient: MoviesApiClient) : RepositoryContract {

    override suspend fun getPopularMovies(): DataResource<List<Movie>> {
        val movies = webApiClient.getPopularMovies()
        return prepareData(movies) as DataResource<List<Movie>>
    }

    override suspend fun getTopRatedMovies(): DataResource<List<Movie>> {
        val movies = webApiClient.getTopRatedMovies()
        return prepareData(movies) as DataResource<List<Movie>>
    }

    override suspend fun getNowPlayingMovies(): DataResource<List<Movie>> {
        val movies = webApiClient.getNowPlayingMovies()
        return prepareData(movies) as DataResource<List<Movie>>
    }


    override suspend fun getCreditsByMovieId(id: Long): DataResource<List<Cast>> {
        val credits = webApiClient.getCreditsByMovieId(id)
        return prepareData(credits) as DataResource<List<Cast>>
    }

    private fun prepareData(
        resultApi: Response<*>
    ): DataResource<List<Any>> {
        if (!resultApi.isSuccessful) {
            return DataResource.Error("Error net TODO", emptyList())
        } else {
            resultApi.body().let {
                return if (it != null) {
                    when (it) {
                        is CreditsResult -> {
                            DataResource.Success(it.cast as List<Any>)
                        }
                        is MovieResult -> {
                            DataResource.Success(it.results as List<Any>)
                        }
                        else -> DataResource.Error("Error cast list", emptyList())
                    }

                } else {
                    DataResource.Error("Error net TODO", emptyList())
                }
            }
        }
    }


}