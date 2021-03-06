package com.astronout.androidtestdot.network.repositories

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.astronout.androidtestdot.network.ServiceFactory
import com.astronout.androidtestdot.users.model.GetUsersModel
import com.astronout.androidtestdot.utils.State
import com.astronout.androidtestdot.utils.logError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class UsersDataSource(private val scope: CoroutineScope) : PageKeyedDataSource<Int, GetUsersModel>() {

    private val restApi = ServiceFactory.create()

    var state: MutableLiveData<State> = MutableLiveData()

    private val STARTING_PAGE_INDEX = 1

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, GetUsersModel>) {
        updateState(State.LOADING)
        scope.launch {
            try {
                val response = restApi.getUsers(STARTING_PAGE_INDEX)
                when {
                    response.isSuccessful -> {
                        updateState(State.DONE)
                        val getUsersModel = response.body()?.data
                        callback.onResult(getUsersModel ?: listOf(), null, STARTING_PAGE_INDEX + 1)
                    }
                }

            } catch (exception : Exception){
                updateState(State.ERROR)
                logError("PostsDataSource: Failed to fetch data!")
            }

        }

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GetUsersModel>) {
        updateState(State.LOADING)
        scope.launch {
            try {
                val response = restApi.getUsers(params.key)
                when {
                    response.isSuccessful -> {
                        updateState(State.DONE)
                        val getUsersModel = response.body()?.data
                        callback.onResult(getUsersModel ?: listOf(), params.key + 1)
                    }
                }

            } catch (exception : Exception){
                updateState(State.ERROR)
                logError("PostsDataSource: Failed to fetch data!")
            }
        }

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GetUsersModel>) {

    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }

}