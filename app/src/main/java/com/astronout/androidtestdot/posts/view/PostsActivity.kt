package com.astronout.androidtestdot.posts.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.astronout.androidtestdot.R
import com.astronout.androidtestdot.databinding.ActivityPostsBinding
import com.astronout.androidtestdot.posts.adapter.GetPostsAdapter
import com.astronout.androidtestdot.posts.viewmodel.PostsViewModel
import com.astronout.androidtestdot.users.adapter.GetUsersAdapter
import com.astronout.androidtestdot.utils.*

class PostsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostsBinding
    private lateinit var viewModel: PostsViewModel
    private lateinit var adapter: GetPostsAdapter

    companion object {
        const val EXTRA_ID = "EXTRA_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_posts)
        val extraId = intent.getIntExtra(EXTRA_ID, 0)
        viewModel = ViewModelProvider(this).get(PostsViewModel::class.java).apply {
            setUserId(extraId)
        }

        setSupportActionBar(binding.toolbar)

        checkInternetConnectionAndGetData()
        setupSwipeRefreshLayout()
        observeLiveData()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    private fun checkInternetConnectionAndGetData() {
        if (isInternetAvailable()) {
            viewModel.refreshData()
        } else {
            noInternetConnectionAlert {
                checkInternetConnectionAndGetData()
            }
        }
    }

    private fun setupSwipeRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = true
            checkInternetConnectionAndGetData()
        }
    }

    private fun observeLiveData() {
        viewModel.getPosts().observe(this, Observer {
            // delay one second to get the right size
            Handler().postDelayed({
                updateUI(it.size)
            }, 1000)
            adapter = GetPostsAdapter(this, GetPostsAdapter.OnClickListener { getPostModel ->

            })
            adapter.submitList(it)
            binding.rvPosts.adapter = adapter
            if (binding.swipeRefreshLayout.isRefreshing) {
                binding.swipeRefreshLayout.isRefreshing = false
            }
        })
    }

    private fun updateUI(itemCount: Int) {
        if (itemCount == 0) {
            binding.rvPosts.visibility = gone()
            binding.tvNoData.visibility = visible()
        } else {
            binding.rvPosts.visibility = visible()
            binding.tvNoData.visibility = gone()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}