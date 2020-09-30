package com.astronout.androidtestdot.posts.view

import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.astronout.androidtestdot.R
import com.astronout.androidtestdot.baseview.BaseActivity
import com.astronout.androidtestdot.databinding.ActivityPostsBinding
import com.astronout.androidtestdot.posts.adapter.GetPostsAdapter
import com.astronout.androidtestdot.posts.model.GetPostsModel
import com.astronout.androidtestdot.posts.viewmodel.PostsViewModel
import com.astronout.androidtestdot.utils.*
import com.astronout.androidtestdot.utils.glide.GlideApp
import com.bumptech.glide.GenericTransitionOptions

class PostsActivity : BaseActivity() {

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
        initState()

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
            adapter = GetPostsAdapter(this, GetPostsAdapter.OnClickListener { getPostModel, image ->
                showPopup(getPostModel, image)
            })
            adapter.submitList(it)
            binding.rvPosts.adapter = adapter
            if (binding.swipeRefreshLayout.isRefreshing) {
                binding.swipeRefreshLayout.isRefreshing = false
            }
        })
    }

    private fun showPopup(getPostsModel: GetPostsModel, image: Int) {
        val window = PopupWindow(this)
        val popupView = layoutInflater.inflate(R.layout.layout_popup, null)
        window.contentView = popupView
        val popupImage = popupView.findViewById<AppCompatImageView>(R.id.popup_image)
        val popupTitle = popupView.findViewById<AppCompatTextView>(R.id.popup_title)
        val popupBody = popupView.findViewById<AppCompatTextView>(R.id.popup_body)
        val popupClose = popupView.findViewById<AppCompatImageView>(R.id.popup_close)
        GlideApp.with(this)
            .load(image)
            .transition(GenericTransitionOptions.with(android.R.anim.fade_in))
            .into(popupImage)
        popupTitle.text = getPostsModel.title
        popupBody.text = getPostsModel.body
        popupClose.setOnClickListener { window.dismiss() }
        window.showAtLocation(popupView, Gravity.CENTER, 0, 0)
    }

    private fun updateUI() {
        if (viewModel.listIsEmpty()) {
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

    private fun initState() {
        viewModel.getState().observe(this, Observer { state ->
            if (viewModel.listIsEmpty() && state == State.LOADING) {
                progress.show()
            } else {
                progress.dismiss()
            }
            updateUI()
        })
    }

}