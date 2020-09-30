package com.astronout.androidtestdot.posts.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class GetPostsModel(
    @SerializedName("body")
    val body: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: Int
): Parcelable