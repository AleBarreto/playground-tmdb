package com.barreto.playgroundtmdb.feature.detail

import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barreto.playgroundtmdb.R
import com.barreto.playgroundtmdb.feature.setImageUrl
import com.barreto.playgroundtmdb.feature.setVoteAverage

import com.barreto.playgroundtmdb.model.Movie
import com.barreto.playgroundtmdb.repository.NetworkDataSource
import com.barreto.playgroundtmdb.services.WebApiAccess

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
        val tvTitle: TextView = findViewById(R.id.tv_title)
        val tvStudio: TextView = findViewById(R.id.tv_studio)
        val tvGenre: TextView = findViewById(R.id.tv_genre)
        val tvAbout: TextView = findViewById(R.id.tv_about_movie)
        val ratingBar: RatingBar = findViewById(R.id.rating_bar)

        tvTitle.text = movie.title
        tvAbout.text = movie.overview
        ratingBar.setVoteAverage(movie.voteAverage)

        rvList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        imageView.setImageUrl("https://image.tmdb.org/t/p/w500" + movie.backdropPath)

        viewModel.getCreditsById(movie.id)
        viewModel.getMovieDetail(movie.id)

        viewModel.credits.observe(this, Observer {
            rvList.adapter = AdapterCastMovie(it)
        })

        viewModel.genres.observe(this, Observer {
            tvGenre.text = it

        })

        viewModel.companies.observe(this, Observer {
            tvStudio.text = it
        })


    }

    companion object {
        const val EXTRA_MOVIE = "movie_key"
    }

}