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
import com.barreto.playgroundtmdb.feature.setImageWithCollapsingToolbar
import com.barreto.playgroundtmdb.feature.setVoteAverage
import com.barreto.playgroundtmdb.model.Movie
import com.barreto.playgroundtmdb.repository.MovieRepository
import com.barreto.playgroundtmdb.repository.NetworkDataSource
import com.barreto.playgroundtmdb.services.WebApiAccess
import com.google.android.material.appbar.CollapsingToolbarLayout


class DetailMovieActivity : AppCompatActivity() {

    private val viewModel by viewModels<DetailViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val dataSource = NetworkDataSource(WebApiAccess.moviesApi)
                val repository = MovieRepository(dataSource)
                return DetailViewModel(repository) as T
            }
        }
    }

    private lateinit var imageMain: ImageView
    private lateinit var rvList: RecyclerView
    private lateinit var tvTitleMovie: TextView
    private lateinit var tvStudio: TextView
    private lateinit var tvGenre: TextView
    private lateinit var tvRelease: TextView
    private lateinit var tvAboutMovie: TextView
    private lateinit var ratingBar: RatingBar
    private lateinit var collapsingToolbar: CollapsingToolbarLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        val movie: Movie = intent.getSerializableExtra(EXTRA_MOVIE) as Movie
        bindViews()
        setData(movie = movie)
        setListeners()

    }

    private fun bindViews() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        imageMain = findViewById(R.id.backdrop)
        rvList = findViewById(R.id.rv_credits)
        rvList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        tvTitleMovie = findViewById(R.id.tv_title)
        tvStudio = findViewById(R.id.tv_studio)
        tvGenre = findViewById(R.id.tv_genre)
        tvRelease = findViewById(R.id.tv_release)
        tvAboutMovie = findViewById(R.id.tv_about_movie)
        ratingBar = findViewById(R.id.rating_bar)
        collapsingToolbar = findViewById(R.id.collapsing_toolbar)
    }

    private fun setData(movie: Movie) {
        viewModel.getCreditsById(movie.id)
        viewModel.getMovieDetail(movie.id)
        tvTitleMovie.text = movie.title
        tvAboutMovie.text = movie.overview
        ratingBar.setVoteAverage(movie.voteAverage)
        imageMain.setImageWithCollapsingToolbar(movie.backdropPath, collapsingToolbar)
        tvRelease.text = movie.releaseDate.substring(0, 4)

    }

    private fun setListeners() {
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