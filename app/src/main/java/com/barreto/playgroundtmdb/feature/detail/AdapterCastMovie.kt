package com.barreto.playgroundtmdb.feature.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barreto.playgroundtmdb.R
import com.barreto.playgroundtmdb.feature.setImageUrlCircle
import com.barreto.playgroundtmdb.model.Cast

class AdapterCastMovie(private val list: List<Cast>) :
    RecyclerView.Adapter<AdapterCastMovie.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivCast: ImageView = itemView.findViewById(R.id.iv_cast)
        private val tvCast: TextView = itemView.findViewById(R.id.tv_cast)

        fun bindView(cast: Cast) {
            tvCast.text = cast.name
            ivCast.setImageUrlCircle("https://image.tmdb.org/t/p/w200" + cast.profilePath)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_detail_cast, parent, false)
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(list[position])
    }

}