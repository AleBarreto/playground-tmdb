package com.barreto.playgroundtmdb.feature

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barreto.playgroundtmdb.R
import com.barreto.playgroundtmdb.feature.home.domain.DataSourceHomeMain

class AdapterHomeMain(private val listData: List<DataSourceHomeMain>) :
    RecyclerView.Adapter<AdapterHomeMain.ViewHolderMain>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain {
        return ViewHolderMain(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_home, parent, false)
        )
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ViewHolderMain, position: Int) {
        holder.bindView(listData[position])
    }

    class ViewHolderMain(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvGenre: TextView = itemView.findViewById(R.id.tv_genre)
        private val recyclerView: RecyclerView = itemView.findViewById(R.id.rv_content_movie)

        fun bindView(data: DataSourceHomeMain) {
            tvGenre.text = data.genre

            recyclerView.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)

            recyclerView.adapter = AdapterHomeMovie(data.movies)

        }

    }

}