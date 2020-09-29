package com.astronout.androidtestdot.network.repositories

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.astronout.androidtestdot.users.model.GetUsersModel
import kotlinx.coroutines.CoroutineScope

class UsersDataSourceFactory(private val scope: CoroutineScope) : DataSource.Factory<Int, GetUsersModel>() {

    val usersDataSourceLiveData = MutableLiveData<UsersDataSource>()
    lateinit var latestSource: UsersDataSource

    override fun create(): DataSource<Int, GetUsersModel> {
        latestSource = UsersDataSource(scope)
        usersDataSourceLiveData.postValue(latestSource)
        return latestSource
    }
}