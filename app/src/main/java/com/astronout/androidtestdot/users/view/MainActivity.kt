package com.astronout.androidtestdot.users.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.astronout.androidtestdot.R
import com.astronout.androidtestdot.baseview.BaseActivity
import com.astronout.androidtestdot.databinding.ActivityMainBinding
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

        setupSwipeRefreshLayout()
        observeLiveData()

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
            checkInternetConnection()
        }
    }

    private fun observeLiveData() {
        viewModel.getPosts().observe(this, Observer {
            adapter = GetUsersAdapter(this, GetUsersAdapter.OnClickListener { getUsersModel ->
                showToast(getUsersModel.name)
            })
            adapter.submitList(it)
            binding.rvUsers.adapter = adapter
            if (binding.swipeRefreshLayout.isRefreshing) {
                binding.swipeRefreshLayout.isRefreshing = false
            }
        })
    }

}