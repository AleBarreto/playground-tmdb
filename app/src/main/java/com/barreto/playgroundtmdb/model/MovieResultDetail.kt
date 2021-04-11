package com.barreto.playgroundtmdb.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResultDetail(
    val genres: List<Genre>,
    @SerializedName(value = "production_companies")
    val productionCompanies: List<Company>
) : Parcelable

@Parcelize
data class Genre(
    val id: Long,
    val name: String
) : Parcelable

@Parcelize
data class Company(
    val id: Long,
    @SerializedName(value = "logo_path")
    val logoPath: String,
    val name: String,
    @SerializedName(value = "origin_country")
    val originCountry: String
) : Parcelable

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