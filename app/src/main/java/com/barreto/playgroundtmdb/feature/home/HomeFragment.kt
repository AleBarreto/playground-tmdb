package com.barreto.playgroundtmdb.feature.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.barreto.playgroundtmdb.R
import com.barreto.playgroundtmdb.model.Movie
import com.barreto.playgroundtmdb.repository.MovieRepository
import com.barreto.playgroundtmdb.repository.NetworkDataSource
import com.barreto.playgroundtmdb.services.WebApiAccess


class HomeFragment : Fragment(R.layout.fragment_home), AdapterHomeMain.OnClickMovie {

    private val viewModel by viewModels<HomeViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val dataSource = NetworkDataSource(WebApiAccess.moviesApi)
                val repository = MovieRepository(dataSource)
                return HomeViewModel(repository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv: RecyclerView = view.findViewById(R.id.rv_home_main)

        viewModel.getAllMovies()

        viewModel.listDataHomeMain.observe(viewLifecycleOwner, {
            val adapterHomeMain = AdapterHomeMain(it)
            adapterHomeMain.setOnClickMovie(this)
            rv.adapter = adapterHomeMain
        })


    }

    override fun onClickMovie(movie: Movie) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToDetailMovieFragment(movie = movie)
        findNavController().navigate(action)
    }


}