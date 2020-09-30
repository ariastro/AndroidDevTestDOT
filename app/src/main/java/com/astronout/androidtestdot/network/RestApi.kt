package com.astronout.androidtestdot.network

import com.astronout.androidtestdot.posts.model.GetPostResponseModel
import com.astronout.androidtestdot.users.model.GetUsersResponseModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface RestApi {

    @GET("users")
    suspend fun getUsers(@Query("page") page: Int) : Response<GetUsersResponseModel>

    @GET("posts")
    suspend fun getPosts(@Query("user_id") userId: Int,
                         @Query("page") page: Int) : Response<GetPostResponseModel>

}