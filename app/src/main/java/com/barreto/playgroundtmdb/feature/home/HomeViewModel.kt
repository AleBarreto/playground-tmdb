package com.barreto.playgroundtmdb.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barreto.playgroundtmdb.model.Movie
import com.barreto.playgroundtmdb.repository.RepositoryContract
import com.barreto.playgroundtmdb.services.Result
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: RepositoryContract) : ViewModel() {

    private val _listDataHomeMain = MutableLiveData<List<WrapperDataMovie>>()
    val listDataHomeMain: LiveData<List<WrapperDataMovie>> get() = _listDataHomeMain

    fun getAllMovies() = viewModelScope.launch {


        val listAll: ArrayList<WrapperDataMovie> = arrayListOf()
        listAll.add(prepareData("Popular", repository.getPopularMovies()))
        listAll.add(prepareData("Top Rated", repository.getTopRatedMovies()))
        listAll.add(prepareData("Now Playing", repository.getNowPlayingMovies()))
        _listDataHomeMain.postValue(listAll)
    }

    private fun prepareData(
        genre: String,
        response: Result<List<Movie>>
    ): WrapperDataMovie {
        return when (response) {
            is Result.Success -> {
                if (response.data != null) {
                    WrapperDataMovie(genre, response.data)
                } else {
                    WrapperDataMovie(genre, emptyList())
                }
            }
            else -> {
                WrapperDataMovie(genre, emptyList())
            }
        }
    }


}

// Wrapper created to add genre to the movie list
data class WrapperDataMovie(
    val genre: String,
    val movies: List<Movie>
)