package com.astronout.androidtestdot.network.repositories

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.astronout.androidtestdot.posts.model.GetPostsModel
import kotlinx.coroutines.CoroutineScope

class PostsDataSourceFactory(private val scope: CoroutineScope) : DataSource.Factory<Int, GetPostsModel>() {

    val postsDataSourceLiveData = MutableLiveData<PostsDataSource>()
    lateinit var latestSource: PostsDataSource
    var userId: Int = 0

    override fun create(): DataSource<Int, GetPostsModel> {
        latestSource = PostsDataSource(userId, scope)
        postsDataSourceLiveData.postValue(latestSource)
        return latestSource
    }
}