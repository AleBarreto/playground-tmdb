package com.barreto.playgroundtmdb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barreto.playgroundtmdb.feature.home.domain.DataSourceHomeMain
import com.barreto.playgroundtmdb.model.Movie
import com.barreto.playgroundtmdb.repository.RepositoryContract
import com.barreto.playgroundtmdb.services.DataResource
import kotlinx.coroutines.launch

class MainViewModel(private val repository: RepositoryContract) : ViewModel() {

    private val _listDataHomeMain = MutableLiveData<List<DataSourceHomeMain>>()
    val listDataHomeMain: LiveData<List<DataSourceHomeMain>> get() = _listDataHomeMain

    fun getAllMovies() = viewModelScope.launch {


        val listAll: ArrayList<DataSourceHomeMain> = arrayListOf()
        listAll.add(prepareData("Popular", repository.getPopularMovies()))
        listAll.add(prepareData("Top Rated", repository.getTopRatedMovies()))
        listAll.add(prepareData("Now Playing", repository.getNowPlayingMovies()))
        _listDataHomeMain.postValue(listAll)
    }

    private fun prepareData(
        genre: String,
        response: DataResource<List<Movie>>
    ): DataSourceHomeMain {
        return when (response) {
            is DataResource.Success -> {
                if (response.data != null) {
                    DataSourceHomeMain(genre, response.data)
                } else {
                    DataSourceHomeMain(genre, emptyList())
                }
            }
            else -> {
                DataSourceHomeMain(genre, emptyList())
            }
        }
    }


}