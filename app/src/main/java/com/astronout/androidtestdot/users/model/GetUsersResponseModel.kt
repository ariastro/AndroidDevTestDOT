package com.astronout.androidtestdot.users.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class GetUsersResponseModel(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: List<GetUsersModel>,
    @SerializedName("meta")
    val meta: Meta
)