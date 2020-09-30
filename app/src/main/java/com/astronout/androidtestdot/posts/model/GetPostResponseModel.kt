package com.astronout.androidtestdot.posts.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class GetPostResponseModel(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: List<GetPostsModel>,
    @SerializedName("meta")
    val meta: Meta
)