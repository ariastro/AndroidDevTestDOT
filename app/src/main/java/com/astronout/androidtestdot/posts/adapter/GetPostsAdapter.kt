package com.astronout.androidtestdot.posts.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.astronout.androidtestdot.R
import com.astronout.androidtestdot.databinding.ItemPostBinding
import com.astronout.androidtestdot.posts.model.GetPostsModel
import com.astronout.androidtestdot.utils.glide.GlideApp
import com.bumptech.glide.GenericTransitionOptions

class GetPostsAdapter(private val context: Context, private val onClickListener: OnClickListener) : PagedListAdapter<GetPostsModel, GetPostsAdapter.GetPostViewHolder>(DiffCallback) {

    private val images = intArrayOf(
        R.drawable.img1,
        R.drawable.img2,
        R.drawable.img3,
        R.drawable.img4,
        R.drawable.img5,
        R.drawable.img6,
        R.drawable.img7,
        R.drawable.img8,
        R.drawable.img9,
        R.drawable.img10
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetPostViewHolder =
        GetPostViewHolder.from(parent)

    override fun onBindViewHolder(holder: GetPostViewHolder, position: Int) {
        val getPostsModel = getItem(position)
        getPostsModel?.let { holder.bind(it) }

        GlideApp.with(context)
            .load(images.random())
            .transition(GenericTransitionOptions.with(android.R.anim.fade_in))
            .into(holder.binding.imageviewBackground)

        holder.binding.itemLayout.setOnClickListener {
            if (getPostsModel != null) {
                onClickListener.onClick(getPostsModel)
            }
        }

    }

    class GetPostViewHolder(val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(getPostsModel: GetPostsModel) {
            binding.itemPost = getPostsModel
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): GetPostViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemPostBinding.inflate(layoutInflater, parent, false)
                return GetPostViewHolder(binding)
            }
        }
    }

    class OnClickListener(val clickListener: (getPostsModel: GetPostsModel) -> Unit) {
        fun onClick(getPostsModel: GetPostsModel) = clickListener(getPostsModel) }

    private companion object DiffCallback : DiffUtil.ItemCallback<GetPostsModel>() {

        override fun areItemsTheSame(oldItem: GetPostsModel, newItem: GetPostsModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: GetPostsModel, newItem: GetPostsModel): Boolean {
            return oldItem.id == newItem.id
        }

    }

}