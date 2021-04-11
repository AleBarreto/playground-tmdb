package com.barreto.playgroundtmdb.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreditsResult(
    val id: Long,
    val cast: List<Cast>
) : Parcelable

@Parcelize
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
) : Parcelable
