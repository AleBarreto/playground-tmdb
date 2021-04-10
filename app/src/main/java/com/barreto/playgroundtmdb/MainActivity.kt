package com.barreto.playgroundtmdb

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barreto.playgroundtmdb.feature.detail.DetailMovieActivity
import com.barreto.playgroundtmdb.feature.home.AdapterHomeMain
import com.barreto.playgroundtmdb.feature.home.HomeViewModel
import com.barreto.playgroundtmdb.model.Movie
import com.barreto.playgroundtmdb.repository.MovieRepository
import com.barreto.playgroundtmdb.repository.NetworkDataSource
import com.barreto.playgroundtmdb.services.WebApiAccess

class MainActivity : AppCompatActivity(), AdapterHomeMain.OnClickMovie {

    private val viewModel by viewModels<HomeViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val dataSource = NetworkDataSource(WebApiAccess.moviesApi)
                val repository = MovieRepository(dataSource)
                return HomeViewModel(repository) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_home)

        val rv: RecyclerView = findViewById(R.id.rv_home_main)
        rv.layoutManager = LinearLayoutManager(this)

        viewModel.getAllMovies()

        viewModel.listDataHomeMain.observe(this, Observer {
            val adapterHomeMain = AdapterHomeMain(it)
            adapterHomeMain.setOnClickMovie(this)
            rv.adapter = adapterHomeMain
        })


    }

    override fun onClickMovie(movie: Movie) {
        val intent = Intent(this, DetailMovieActivity::class.java)
        intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie)
        startActivity(intent)

    }
}