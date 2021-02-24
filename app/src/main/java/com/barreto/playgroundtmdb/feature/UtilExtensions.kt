package com.barreto.playgroundtmdb.feature

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.RatingBar
import androidx.annotation.Nullable
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.appbar.CollapsingToolbarLayout

fun RatingBar.setVoteAverage(value: Double) {
    this.rating = (value / 2).toFloat()
}

fun ImageView.setImageUrl(size: String, urlPath: String) {
    Glide.with(this.context)
        .load("https://image.tmdb.org/t/p/w$size$urlPath")
        .centerCrop()
        .into(this)
}

fun ImageView.setImageUrlCircle(size: String, urlPath: String) {
    Glide.with(this.context)
        .load("https://image.tmdb.org/t/p/w$size$urlPath")
        .circleCrop()
        .into(this)
}

fun ImageView.setImageWithCollapsingToolbar(
    path: String,
    collapsingToolbar: CollapsingToolbarLayout
) {
    Glide.with(this.context).asBitmap().load("https://image.tmdb.org/t/p/w500$path")
        .into(object : CustomTarget<Bitmap?>() {
            override fun onResourceReady(
                resource: Bitmap,
                @Nullable transition: Transition<in Bitmap?>?
            ) {
                this@setImageWithCollapsingToolbar.setImageBitmap(resource)
                val palette = Palette.from(resource).generate()
                collapsingToolbar.setContentScrimColor(palette.getMutedColor(Color.BLACK))
                collapsingToolbar.setStatusBarScrimColor(palette.getDarkMutedColor(Color.BLACK))
            }

            override fun onLoadCleared(@Nullable placeholder: Drawable?) {}
        })

}

