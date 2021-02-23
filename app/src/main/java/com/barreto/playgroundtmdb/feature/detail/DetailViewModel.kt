package com.barreto.playgroundtmdb.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barreto.playgroundtmdb.model.Cast
import com.barreto.playgroundtmdb.repository.RepositoryContract
import com.barreto.playgroundtmdb.services.DataResource
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: RepositoryContract) : ViewModel() {


    private val _credits = MutableLiveData<List<Cast>>()
    val credits: LiveData<List<Cast>> get() = _credits

    fun getCreditsById(id: Long) = viewModelScope.launch {

        when (val response = repository.getCreditsByMovieId(id)) {
            is DataResource.Success -> {
                response.data.let {
                    _credits.postValue(it)
                }
            }
            else -> {
                //TODO Error
                _credits.postValue(emptyList())
            }
        }


    }

}