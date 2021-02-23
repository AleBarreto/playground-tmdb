package com.barreto.playgroundtmdb.feature.detail

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barreto.playgroundtmdb.R
import com.barreto.playgroundtmdb.model.Movie
import com.barreto.playgroundtmdb.repository.NetworkDataSource
import com.barreto.playgroundtmdb.services.WebApiAccess
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailMovieActivity : AppCompatActivity() {

    private val viewModel by viewModels<DetailViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val dataSource = NetworkDataSource(WebApiAccess.moviesApi)
                return DetailViewModel(dataSource) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        val movie: Movie = intent.getSerializableExtra(EXTRA_MOVIE) as Movie

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val imageView: ImageView = findViewById(R.id.backdrop)
        val rvList: RecyclerView = findViewById(R.id.rv_credits)

        rvList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        Glide.with(imageView)
            .load("https://image.tmdb.org/t/p/w500" + movie.backdropPath)
            .apply(RequestOptions.centerCropTransform())
            .into(imageView)

        viewModel.getCreditsById(movie.id)

        viewModel.credits.observe(this, Observer {
            rvList.adapter = AdapterCastMovie(it)
        })


    }

    companion object {
        const val EXTRA_MOVIE = "movie_key"
    }

}