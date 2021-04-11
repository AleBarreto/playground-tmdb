package com.barreto.playgroundtmdb.feature.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.barreto.playgroundtmdb.R
import com.barreto.playgroundtmdb.feature.home.domain.DataSourceHomeMain
import com.barreto.playgroundtmdb.model.Movie

class AdapterHomeMain :
    ListAdapter<DataSourceHomeMain, AdapterHomeMain.ViewHolderMain>(HomeDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain {
        return ViewHolderMain(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_home, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolderMain, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bindView(currentItem)
        }
    }

    inner class ViewHolderMain(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvGenre: TextView = itemView.findViewById(R.id.tv_genre)
        private val recyclerView: RecyclerView = itemView.findViewById(R.id.rv_content_movie)

        fun bindView(data: DataSourceHomeMain) {
            tvGenre.text = data.genre

            recyclerView.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)

            val adapterHomeMovie = AdapterHomeMovie()
            adapterHomeMovie.setOnClickMovie { clickMovie(it) }
            recyclerView.adapter = adapterHomeMovie
            adapterHomeMovie.submitList(data.movies)
        }

    }

    companion object {
        private val HomeDiffCallback = object : DiffUtil.ItemCallback<DataSourceHomeMain>() {
            override fun areItemsTheSame(
                oldItem: DataSourceHomeMain,
                newItem: DataSourceHomeMain
            ): Boolean {
                return oldItem.genre == newItem.genre
            }

            override fun areContentsTheSame(
                oldItem: DataSourceHomeMain,
                newItem: DataSourceHomeMain
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    //Click from adapter AdapterHomeMovie
    private fun clickMovie(movie: Movie) {
        onClick.onClickMovie(movie)
    }

    interface OnClickMovie {
        fun onClickMovie(movie: Movie)
    }

    private lateinit var onClick: OnClickMovie
    fun setOnClickMovie(onClick: OnClickMovie) {
        this.onClick = onClick
    }


}