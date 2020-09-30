package com.astronout.androidtestdot.posts.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.astronout.androidtestdot.network.repositories.PostsDataSource
import com.astronout.androidtestdot.network.repositories.PostsDataSourceFactory
import com.astronout.androidtestdot.posts.model.GetPostsModel
import com.astronout.androidtestdot.utils.State

class PostsViewModel(application: Application): AndroidViewModel(application) {

    var postsLiveData: LiveData<PagedList<GetPostsModel>>

    val dataSourceFactory = PostsDataSourceFactory(viewModelScope)

    fun setUserId(userId: Int) {
        dataSourceFactory.userId = userId
    }

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(5)
            .setEnablePlaceholders(false)
            .build()
        postsLiveData = LivePagedListBuilder<Int, GetPostsModel>(dataSourceFactory, config).build()
    }

    fun getState(): LiveData<State> = Transformations.switchMap<PostsDataSource,
            State>(dataSourceFactory.postsDataSourceLiveData, PostsDataSource::state)

    fun listIsEmpty(): Boolean {
        return postsLiveData.value?.isEmpty() ?: true
    }

    fun getPosts() = postsLiveData

    fun refreshData() {
        dataSourceFactory.postsDataSourceLiveData.value?.invalidate()
    }

}