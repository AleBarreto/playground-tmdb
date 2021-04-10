package com.barreto.playgroundtmdb

import com.barreto.playgroundtmdb.model.Movie
import com.barreto.playgroundtmdb.repository.MovieRepository
import com.barreto.playgroundtmdb.services.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class NetworkDataSourceTest {

    private val movie1 = Movie(
        false,
        "/ndlQ2Cuc3cjTL7lTynw6I4boP4S.jpg",
        emptyList(),
        1,
        "en",
        "Suicide Squad",
        "The most dangerous former operative of the CIA is drawn out of hiding to uncover hidden truths about his past.",
        0.0,
        "/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg",
        "2016-08-03",
        "Suicide Squad",
        false,
        5.91,
        1466
    )
    private val remotePopularMovies = listOf(movie1)
    private lateinit var networkDataSource: FakeNetworkDataSource

    // Class under test
    private lateinit var movieRepository: MovieRepository


    @Before
    fun createNetworkDataSource() {
        networkDataSource = FakeNetworkDataSource(remotePopularMovies.toMutableList())
        movieRepository = MovieRepository(networkDataSource)
    }

    @Test
    fun getPopularMovie_requestsAllPopularMovieFromNetWorkDataSource() = runBlockingTest {
        // When tasks are requested from the tasks repository
        val popularMovies = movieRepository.getPopularMovies() as Result.Success

        // Then tasks are loaded from the remote data source
        assertThat(popularMovies.data, IsEqual(remotePopularMovies))
    }


}