package com.astronout.androidtestdot.users.model

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class GetUsersModel(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("updated_at")
    val updatedAt: String
)