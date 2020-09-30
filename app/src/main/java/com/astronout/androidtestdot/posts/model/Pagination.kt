package com.astronout.androidtestdot.posts.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Pagination(
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("total")
    val total: Int
)