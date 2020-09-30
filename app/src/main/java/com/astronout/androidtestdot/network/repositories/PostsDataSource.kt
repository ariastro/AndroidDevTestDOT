package com.astronout.androidtestdot.network.repositories

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.astronout.androidtestdot.network.ServiceFactory
import com.astronout.androidtestdot.posts.model.GetPostsModel
import com.astronout.androidtestdot.utils.State
import com.astronout.androidtestdot.utils.logError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PostsDataSource(private val userId: Int, private val scope: CoroutineScope) : PageKeyedDataSource<Int, GetPostsModel>() {

    private val restApi = ServiceFactory.create()

    var state: MutableLiveData<State> = MutableLiveData()

    private val STARTING_PAGE_INDEX = 1

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, GetPostsModel>) {
        updateState(State.LOADING)
        scope.launch {
            try {
                val response = restApi.getPosts(userId, STARTING_PAGE_INDEX)
                when {
                    response.isSuccessful -> {
                        updateState(State.DONE)
                        val getPostsModel = response.body()?.data
                        callback.onResult(getPostsModel ?: listOf(), null, STARTING_PAGE_INDEX + 1)
                    }
                }

            } catch (exception : Exception){
                updateState(State.ERROR)
                logError("PostsDataSource: Failed to fetch data!")
            }

        }

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GetPostsModel>) {
        updateState(State.LOADING)
        scope.launch {
            try {
                val response = restApi.getPosts(userId, params.key)
                when {
                    response.isSuccessful -> {
                        updateState(State.DONE)
                        val getPostsModel = response.body()?.data
                        callback.onResult(getPostsModel ?: listOf(), params.key + 1)
                    }
                }

            } catch (exception : Exception){
                updateState(State.ERROR)
                logError("PostsDataSource: Failed to fetch data!")
            }
        }

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GetPostsModel>) {

    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }

}