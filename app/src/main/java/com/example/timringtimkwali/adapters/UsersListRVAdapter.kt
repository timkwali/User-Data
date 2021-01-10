package com.example.timringtimkwali.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.timringtimkwali.databinding.FragmentUsersBinding
import com.example.timringtimkwali.databinding.UsersListTemplateBinding
import com.example.timringtimkwali.model.User
import com.squareup.picasso.Picasso
import java.io.File

class UsersListRVAdapter(private var usersList: List<User>, var listener: OnItemClick):
        RecyclerView.Adapter<UsersListRVAdapter.UsersListViewHolder>() {

    inner class UsersListViewHolder(private val binding: UsersListTemplateBinding):
            RecyclerView.ViewHolder(binding.root) {

//        private lateinit var mUser: User

        fun bind(user: User, action: OnItemClick) {
//            this.mUser = user
            binding.apply {
//                Picasso.get().load(user.avatar).into(userTemplateAvatarIv)
//                Glide.with(userTemplateAvatarIv.context)
//                        .load(File(user.avatar))
//                        .into(userTemplateAvatarIv)

//                Glide.with(holder.imageView.getContext())
//                        .load(new File(hotel.imageId2))
//                        .into(holder.imageView);
                userTemplateFullNameTv.text = user.fullName
                userTemplateDateTv.text = user.createdAt
            }

            itemView.setOnClickListener {
                action.onItemClick(user, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListViewHolder {
        val binding = UsersListTemplateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsersListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersListViewHolder, position: Int) {
        val currentUser = usersList[position]
        holder.bind(currentUser, listener)

    }

    override fun getItemCount(): Int {
        return usersList.size
    }
}

interface OnItemClick {
    fun onItemClick(item: User, position: Int)
}