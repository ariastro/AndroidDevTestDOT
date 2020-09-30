package com.astronout.androidtestdot.users.view

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.astronout.androidtestdot.R
import com.astronout.androidtestdot.baseview.BaseActivity
import com.astronout.androidtestdot.databinding.ActivityMainBinding
import com.astronout.androidtestdot.posts.view.PostsActivity
import com.astronout.androidtestdot.posts.view.PostsActivity.Companion.EXTRA_ID
import com.astronout.androidtestdot.users.adapter.GetUsersAdapter
import com.astronout.androidtestdot.users.viewmodel.MainViewModel
import com.astronout.androidtestdot.utils.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: GetUsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.main = viewModel

        setSupportActionBar(binding.toolbar)

        checkInternetConnection()
        setupSwipeRefreshLayout()
        observeLiveData()
        initState()

    }

    private fun checkInternetConnection() {
        if (isInternetAvailable()) {
            viewModel.refreshData()
        } else {
            noInternetConnectionAlert {
                checkInternetConnection()
            }
        }
    }

    private fun setupSwipeRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = true
            checkInternetConnection()
        }
    }

    private fun observeLiveData() {
        viewModel.getUsers().observe(this, Observer {
            adapter = GetUsersAdapter(this, GetUsersAdapter.OnClickListener { getUsersModel ->
                val intent = Intent(this, PostsActivity::class.java)
                intent.putExtra(EXTRA_ID, getUsersModel.id)
                startActivity(intent)
            })
            adapter.submitList(it)
            binding.rvUsers.adapter = adapter
            if (binding.swipeRefreshLayout.isRefreshing) {
                binding.swipeRefreshLayout.isRefreshing = false
            }
        })
    }

    private fun initState() {
        viewModel.getState().observe(this, Observer { state ->
            if (viewModel.listIsEmpty() && state == State.LOADING) {
                progress.show()
            } else {
                progress.dismiss()
            }
        })
    }

}