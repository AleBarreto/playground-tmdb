package com.barreto.playgroundtmdb.feature.detail

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
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

class DetailMovieFragment : Fragment(R.layout.fragment_detail_movie) {

    private lateinit var imageMain: ImageView
    private lateinit var rvList: RecyclerView
    private lateinit var tvTitleMovie: TextView
    private lateinit var tvStudio: TextView
    private lateinit var tvGenre: TextView
    private lateinit var tvRelease: TextView
    private lateinit var tvAboutMovie: TextView
    private lateinit var ratingBar: RatingBar
    private lateinit var collapsingToolbar: CollapsingToolbarLayout

    private val viewModel by viewModels<DetailViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val dataSource = NetworkDataSource(WebApiAccess.moviesApi)
                val repository = MovieRepository(dataSource)
                return DetailViewModel(repository) as T
            }
        }
    }

    private val args by navArgs<DetailMovieFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)

        toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }

        val movie = args.movie
        bindViews(view)
        setData(movie = movie)
        setListeners()

    }

    private fun bindViews(view: View) {
        imageMain = view.findViewById(R.id.backdrop)
        rvList = view.findViewById(R.id.rv_credits)
        rvList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        tvTitleMovie = view.findViewById(R.id.tv_title)
        tvStudio = view.findViewById(R.id.tv_studio)
        tvGenre = view.findViewById(R.id.tv_genre)
        tvRelease = view.findViewById(R.id.tv_release)
        tvAboutMovie = view.findViewById(R.id.tv_about_movie)
        ratingBar = view.findViewById(R.id.rating_bar)
        collapsingToolbar = view.findViewById(R.id.collapsing_toolbar)
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
        viewModel.credits.observe(viewLifecycleOwner, {
            rvList.adapter = AdapterCastMovie(it)
        })

        viewModel.genres.observe(viewLifecycleOwner, {
            tvGenre.text = it

        })

        viewModel.companies.observe(viewLifecycleOwner, {
            tvStudio.text = it
        })
    }

}