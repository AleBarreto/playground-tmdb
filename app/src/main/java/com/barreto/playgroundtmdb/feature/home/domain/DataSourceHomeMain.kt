package com.barreto.playgroundtmdb.feature.home.domain

import com.barreto.playgroundtmdb.model.Movie

data class DataSourceHomeMain(
    val genre: String,
    val movies: List<Movie>
)