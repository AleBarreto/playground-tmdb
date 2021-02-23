package com.barreto.playgroundtmdb.feature.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.barreto.playgroundtmdb.R
import com.barreto.playgroundtmdb.feature.setImageUrl
import com.barreto.playgroundtmdb.feature.setVoteAverage
import com.barreto.playgroundtmdb.model.Movie

//https://image.tmdb.org/t/p/w500/
class AdapterHomeMovie(private val list: List<Movie>) :
    RecyclerView.Adapter<AdapterHomeMovie.ViewHolder>() {

    private lateinit var itemClick: (Movie) -> Unit

    fun setOnClickMovie(itemClick: (Movie) -> Unit) {
        this.itemClick = itemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_home_horizontal, parent, false)
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tv_title_movie)
        private val ivMovie: ImageView = itemView.findViewById(R.id.iv_movie)
        private val ratingBar: RatingBar = itemView.findViewById(R.id.rating_bar)
        private val containerMain: ConstraintLayout = itemView.findViewById(R.id.container_main)
        fun bindView(movie: Movie) {
            tvTitle.text = movie.title
            ratingBar.setVoteAverage(movie.voteAverage)
            ivMovie.setImageUrl("https://image.tmdb.org/t/p/w300" + movie.posterPath)
            containerMain.setOnClickListener { itemClick(movie) }
        }
    }

}