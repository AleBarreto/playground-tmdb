package com.barreto.playgroundtmdb.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieResult(
    val page: Long,
    val results: List<Movie>,

    @SerializedName(value = "total_pages")
    val totalPages: Long,

    @SerializedName(value = "total_results")
    val totalResults: Long
)

data class Movie(
    val adult: Boolean,

    @SerializedName(value = "backdrop_path")
    val backdropPath: String,

    @SerializedName(value = "genre_ids")
    val genreIDS: List<Long>,

    val id: Long,

    @SerializedName(value = "original_language")
    val originalLanguage: String,

    @SerializedName(value = "original_title")
    val originalTitle: String,

    val overview: String,
    val popularity: Double,

    @SerializedName(value = "poster_path")
    val posterPath: String,

    @SerializedName(value = "release_date")
    val releaseDate: String,

    val title: String,
    val video: Boolean,

    @SerializedName(value = "vote_average")
    val voteAverage: Double,

    @SerializedName(value = "vote_count")
    val voteCount: Long
) : Serializable