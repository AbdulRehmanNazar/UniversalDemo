package com.universal.demo.ui.main.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.universal.demo.data.model.User
import com.universal.demo.databinding.ItemLayoutBinding
import com.universal.demo.interfaces.AdapterItemClick

/**
 * @Author: Abdul Rehman
 */

class MainAdapter(private val itemClickListener: AdapterItemClick<User>) :
    ListAdapter<User, MainAdapter.PostViewHolder>(UserAdapterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post, position)
    }

    inner class PostViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User, position: Int) {
            binding.textViewUserName.text = user.name
            binding.textViewUserEmail.text = user.email
            Glide.with(binding.imageViewAvatar.context).load(user.avatar)
                .into(binding.imageViewAvatar)

            itemView.setOnClickListener {
                itemClickListener.onItemClick(
                    getItem(position), adapterPosition, "mainType"
                )
            }
        }
    }

    class UserAdapterDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem
    }
}