package com.astronout.androidtestdot.users.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.astronout.androidtestdot.R
import com.astronout.androidtestdot.databinding.ItemUserBinding
import com.astronout.androidtestdot.users.model.GetUsersModel
import com.astronout.androidtestdot.utils.glide.GlideApp
import com.bumptech.glide.GenericTransitionOptions
import java.util.*

class GetUsersAdapter(private val context: Context, private val onClickListener: OnClickListener) : PagedListAdapter<GetUsersModel, GetUsersAdapter.GetUsersViewHolder>(DiffCallback) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetUsersViewHolder =
        GetUsersViewHolder.from(parent)

    override fun onBindViewHolder(holder: GetUsersViewHolder, position: Int) {
        val getUsersModel = getItem(position)
        getUsersModel?.let { holder.bind(it) }

        GlideApp.with(context)
            .load(images.random())
            .transition(GenericTransitionOptions.with(android.R.anim.fade_in))
            .into(holder.binding.imageviewBackground)

        holder.binding.itemLayout.setOnClickListener {
            if (getUsersModel != null) {
                onClickListener.onClick(getUsersModel)
            }
        }

    }

    class GetUsersViewHolder(val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(getUsersModel: GetUsersModel) {
            binding.itemUser = getUsersModel
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): GetUsersViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemUserBinding.inflate(layoutInflater, parent, false)
                return GetUsersViewHolder(binding)
            }
        }
    }

    class OnClickListener(val clickListener: (getUsersModel: GetUsersModel) -> Unit) {
        fun onClick(getUsersModel: GetUsersModel) = clickListener(getUsersModel) }

    private companion object DiffCallback : DiffUtil.ItemCallback<GetUsersModel>() {

        override fun areItemsTheSame(oldItem: GetUsersModel, newItem: GetUsersModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: GetUsersModel, newItem: GetUsersModel): Boolean {
            return oldItem.id == newItem.id
        }

    }

}