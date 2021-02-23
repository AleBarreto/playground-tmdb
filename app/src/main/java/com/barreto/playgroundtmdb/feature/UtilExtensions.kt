package com.barreto.playgroundtmdb.feature

import android.widget.ImageView
import android.widget.RatingBar
import com.bumptech.glide.Glide

fun RatingBar.setVoteAverage(value: Double) {
    this.rating = (value / 2).toFloat()
}

fun ImageView.setImageUrl(urlPath: String) {
    Glide.with(this.context)
        .load(urlPath)
        .centerCrop()
        .into(this)
}

fun ImageView.setImageUrlCircle(urlPath: String) {
    Glide.with(this.context)
        .load(urlPath)
        .circleCrop()
        .into(this)
}

