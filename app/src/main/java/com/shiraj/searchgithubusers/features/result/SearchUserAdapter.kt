package com.shiraj.searchgithubusers.features.result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.shiraj.searchgithubusers.databinding.ItemGithubUserBinding
import com.shiraj.searchgithubusers.domain.models.User

class SearchUserAdapter : PagingDataAdapter<User, SearchUserAdapter.ViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemGithubUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let(holder::bind)
    }

    class ViewHolder(
        private val binding: ItemGithubUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) = with(binding) {
            tvUserName.text = user.login
            tvType.text = user.type
            ivUserPhoto.load(user.imageUrl)
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem.login == newItem.login

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem == newItem
        }
    }
}