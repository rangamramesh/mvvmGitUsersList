package com.foo.assignment.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.foo.assignment.R

/**
 * Created by Ramesh on 2019-07-02.
 */

@BindingAdapter("avatarUrl")
fun setUserAvatar(view: ImageView, url: String) {
    Glide.with(view.context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.mipmap.ic_launcher)
        .into(view)
}