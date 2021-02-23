package com.barreto.playgroundtmdb.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barreto.playgroundtmdb.model.Cast
import com.barreto.playgroundtmdb.model.getCompanyFormattedList
import com.barreto.playgroundtmdb.model.getGenreFormattedList
import com.barreto.playgroundtmdb.repository.RepositoryContract
import com.barreto.playgroundtmdb.services.DataResource
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: RepositoryContract) : ViewModel() {


    private val _credits = MutableLiveData<List<Cast>>()
    val credits: LiveData<List<Cast>> get() = _credits
    private val _genres = MutableLiveData<String>()
    val genres: LiveData<String> get() = _genres
    private val _companies = MutableLiveData<String>()
    val companies: LiveData<String> get() = _companies

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

    fun getMovieDetail(id: Long) = viewModelScope.launch {
        when (val response = repository.getMovieDetail(id)) {
            is DataResource.Success -> {
                response.data?.let {
                    val genreFormattedList = it.getGenreFormattedList(it.genres)
                    _genres.postValue(genreFormattedList)
                    val companyFormattedList = it.getCompanyFormattedList(it.productionCompanies)
                    _companies.postValue(companyFormattedList)
                }
            }
            else -> {
                //TODO Error

            }
        }
    }

}