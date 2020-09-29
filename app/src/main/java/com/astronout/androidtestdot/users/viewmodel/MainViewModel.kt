package com.astronout.androidtestdot.users.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.astronout.androidtestdot.network.repositories.UsersDataSource
import com.astronout.androidtestdot.network.repositories.UsersDataSourceFactory
import com.astronout.androidtestdot.users.model.GetUsersModel

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var usersLiveData: LiveData<PagedList<GetUsersModel>>

    val dataSourceFactory = UsersDataSourceFactory(viewModelScope)

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(78)
            .setEnablePlaceholders(false)
            .build()
        usersLiveData = LivePagedListBuilder<Int, GetUsersModel>(dataSourceFactory, config).build()
    }

    fun getPosts() = usersLiveData

    fun refreshData() {
        dataSourceFactory.usersDataSourceLiveData.value?.invalidate()
    }

}