package com.foo.assignment.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.foo.assignment.R
import com.foo.assignment.data.model.User
import kotlinx.android.synthetic.main.item_user.view.*

class UserViewHolder(view: View, private val clickListener: (View, User) -> Unit) : RecyclerView.ViewHolder(view) {

    fun bindTo(user: User?) {
        itemView.tv_profile_name.text = user?.login
        itemView.tv_repos_url.text = user?.reposUrl
        Glide.with(itemView.context)
            .load(user?.avatarUrl)
            .placeholder(R.mipmap.ic_launcher)
            .into(itemView.iv_profile_pic)

        itemView.userContainer.setOnClickListener {
            if (user != null) {
                clickListener(itemView.iv_profile_pic, user)
            }
        }
        ViewCompat.setTransitionName(itemView.iv_profile_pic, user?.login)
    }

    companion object {
        fun create(parent: ViewGroup, clickListener: (View, User) -> Unit, isGridView: Boolean): UserViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view =
                layoutInflater.inflate(
                    if (isGridView) R.layout.item_user_grid
                    else R.layout.item_user, parent, false
                )
            return UserViewHolder(view, clickListener)
        }
    }

}