package com.barreto.playgroundtmdb.repository

import com.barreto.playgroundtmdb.model.Cast
import com.barreto.playgroundtmdb.model.Movie
import com.barreto.playgroundtmdb.model.MovieResultDetail
import com.barreto.playgroundtmdb.services.Result


class MovieRepository(private val netWorkDataSource: MovieDataSource) : RepositoryContract {

    override suspend fun getPopularMovies(): Result<List<Movie>> {
        val popularMovies = netWorkDataSource.getPopularMovies()
        return prepareListMovie(popularMovies)
    }

    override suspend fun getTopRatedMovies(): Result<List<Movie>> {
        val topRatedMovies = netWorkDataSource.getTopRatedMovies()
        return prepareListMovie(topRatedMovies)
    }

    override suspend fun getNowPlayingMovies(): Result<List<Movie>> {
        val nowPlayingMovies = netWorkDataSource.getNowPlayingMovies()
        return prepareListMovie(nowPlayingMovies)
    }

    override suspend fun getCreditsByMovieId(id: Long): Result<List<Cast>> {
        val creditsByMovieId = netWorkDataSource.getCreditsByMovieId(id)
        return if (creditsByMovieId.isNotEmpty()) {
            Result.Success(creditsByMovieId)
        } else {
            Result.Error("Empty List", creditsByMovieId)
        }

    }

    override suspend fun getMovieDetail(id: Long): Result<MovieResultDetail> {
        val movieDetail = netWorkDataSource.getMovieDetail(id)
        return if (movieDetail.genres.isNotEmpty() && movieDetail.productionCompanies.isNotEmpty()) {
            Result.Success(movieDetail)
        } else {
            Result.Error("Empty list", movieDetail)
        }

    }

    private fun prepareListMovie(list: List<Movie>): Result<List<Movie>> {
        return if (list.isNotEmpty()) {
            Result.Success(list)
        } else {
            Result.Error("Empty List", list)
        }
    }

//    override suspend fun getPopularMovies(): Result<List<Movie>> {
//        val movies = webApiClient.getPopularMovies()
//        return prepareData(movies) as Result<List<Movie>>
//    }
//
//    override suspend fun getTopRatedMovies(): Result<List<Movie>> {
//        val movies = webApiClient.getTopRatedMovies()
//        return prepareData(movies) as Result<List<Movie>>
//    }
//
//    override suspend fun getNowPlayingMovies(): Result<List<Movie>> {
//        val movies = webApiClient.getNowPlayingMovies()
//        return prepareData(movies) as Result<List<Movie>>
//    }
//
//
//    override suspend fun getCreditsByMovieId(id: Long): Result<List<Cast>> {
//        val credits = webApiClient.getCreditsByMovieId(id)
//        return prepareData(credits) as Result<List<Cast>>
//    }
//
//    override suspend fun getMovieDetail(id: Long): Result<MovieResultDetail> {
//        val response = webApiClient.getMovieDetail(id)
//        return if (response.isSuccessful && response.body() != null) {
//            Result.Success(response.body()!!)
//        } else {
//            Result.Error("", response.body())
//        }
//    }
//
//    private fun prepareData(
//        resultApi: Response<*>
//    ): Result<List<Any>> {
//        if (!resultApi.isSuccessful) {
//            return Result.Error("Error net TODO", emptyList())
//        } else {
//            resultApi.body().let {
//                return if (it != null) {
//                    when (it) {
//                        is CreditsResult -> {
//                            Result.Success(it.cast as List<Any>)
//                        }
//                        is MovieResult -> {
//                            Result.Success(it.results as List<Any>)
//                        }
//                        else -> Result.Error("Error cast list", emptyList())
//                    }
//
//                } else {
//                    Result.Error("Error net TODO", emptyList())
//                }
//            }
//        }
//    }


}