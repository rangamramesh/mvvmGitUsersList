package com.foo.assignment.ui.detail

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.foo.assignment.R
import com.foo.assignment.data.model.User
import com.foo.assignment.databinding.ActivityDetailBinding
import com.foo.assignment.ui.base.BaseActivity


/**
 * Created by Ramesh on 2019-07-01.
 */
class UserDetailActivity : BaseActivity<UserDetailViewModel, ActivityDetailBinding>(UserDetailViewModel::class.java) {

    companion object {
        const val USER: String = "USER"
        const val TRANSITION_NAME: String = "TRANSITION_NAME"
    }

    lateinit var user: User

    override fun getLayoutRes(): Int {
        return R.layout.activity_detail
    }

    override fun initViewModel(viewModel: UserDetailViewModel) {
        binding.user = user
        binding.executePendingBindings()
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        user = intent!!.extras!!.get(USER) as User
        super.onCreate(savedInstanceState)
        transparentStatusBar()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val imageTransitionName = intent.getStringExtra(TRANSITION_NAME)
            binding.ivProfilePic.transitionName = imageTransitionName
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

}