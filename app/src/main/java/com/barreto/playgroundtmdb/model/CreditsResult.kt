package com.barreto.playgroundtmdb.model

import com.google.gson.annotations.SerializedName

data class CreditsResult(
    val id: Long,
    val cast: List<Cast>
)

data class Cast(
    val adult: Boolean,
    val gender: Long,
    val id: Long,
    @SerializedName("known_for_department")
    val knownForDepartment: String,
    val name: String,
    @SerializedName("original_name")
    val originalName: String,
    val popularity: Double,
    @SerializedName("profile_path")
    val profilePath: String,
    @SerializedName("cast_id")
    val castId: Long,
    val character: String,
    @SerializedName("credit_id")
    val creditId: String,
    val order: Long
)
