package com.astronout.androidtestdot.network.repositories

import androidx.paging.PageKeyedDataSource
import com.astronout.androidtestdot.network.ServiceFactory
import com.astronout.androidtestdot.posts.model.GetPostsModel
import com.astronout.androidtestdot.utils.logError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PostsDataSource(private val userId: Int, private val scope: CoroutineScope) : PageKeyedDataSource<Int, GetPostsModel>() {

    private val restApi = ServiceFactory.create()

    private val STARTING_PAGE_INDEX = 1

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, GetPostsModel>) {
        scope.launch {
            try {
                val response = restApi.getPosts(userId, STARTING_PAGE_INDEX)
                when {
                    response.isSuccessful -> {
                        val getPostsModel = response.body()?.data
                        callback.onResult(getPostsModel ?: listOf(), null, STARTING_PAGE_INDEX + 1)
                    }
                }

            } catch (exception : Exception){
                logError("PostsDataSource: Failed to fetch data!")
            }

        }

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GetPostsModel>) {
        scope.launch {
            try {
                val response = restApi.getPosts(userId, params.key)
                when {
                    response.isSuccessful -> {
                        val getPostsModel = response.body()?.data
                        callback.onResult(getPostsModel ?: listOf(), params.key + 1)
                    }
                }

            } catch (exception : Exception){
                logError("PostsDataSource: Failed to fetch data!")
            }
        }

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GetPostsModel>) {

    }

}