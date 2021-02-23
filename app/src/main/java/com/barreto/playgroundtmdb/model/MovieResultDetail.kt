package com.barreto.playgroundtmdb.model

import com.google.gson.annotations.SerializedName

data class MovieResultDetail(
    val genres: List<Genre>,
    @SerializedName(value = "production_companies")
    val productionCompanies: List<Company>
)


data class Genre(
    val id: Long,
    val name: String
)

data class Company(
    val id: Long,
    @SerializedName(value = "logo_path")
    val logoPath: String,
    val name: String,
    @SerializedName(value = "origin_country")
    val originCountry: String
)

fun MovieResultDetail.getGenreFormattedList(list: List<Genre>): String {
    return list.joinToString(separator = ", ") { genre ->
        genre.name
    }
}

fun MovieResultDetail.getCompanyFormattedList(list: List<Company>): String {
    return list.joinToString(separator = ", ") { company ->
        company.name
    }
}