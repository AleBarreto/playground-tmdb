package com.barreto.playgroundtmdb.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barreto.playgroundtmdb.model.Movie
import com.barreto.playgroundtmdb.repository.RepositoryContract
import com.barreto.playgroundtmdb.services.DataResource
import kotlinx.coroutines.launch

class MainViewModel(private val repository: RepositoryContract) : ViewModel() {


    private val _list = MutableLiveData<List<Movie>>()
    val list: LiveData<List<Movie>> get() = _list

    fun getPopularMovies() = viewModelScope.launch {
        when(val response = repository.getPopularMovies()){
            is DataResource.Success -> {
                _list.postValue(response.data!!)
            }
            is DataResource.Error -> {
                Log.d("COSTA","Messagem error = "+response.message)
            }
            else -> {

            }
        }

    }

}