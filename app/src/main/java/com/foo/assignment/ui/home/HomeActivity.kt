package com.foo.assignment.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.foo.assignment.R
import com.foo.assignment.data.model.User
import com.foo.assignment.data.repository.NetworkState
import com.foo.assignment.data.repository.Status
import com.foo.assignment.databinding.ActivityHomeBinding
import com.foo.assignment.ui.base.BaseActivity
import com.foo.assignment.ui.detail.UserDetailActivity
import com.foo.assignment.ui.home.adapter.UserAdapter
import kotlinx.android.synthetic.main.item_network_state.view.*


class HomeActivity : BaseActivity<HomeViewModel, ActivityHomeBinding>(HomeViewModel::class.java) {

    private lateinit var userAdapter: UserAdapter
    private var isGridView: Boolean = false

    override fun initViewModel(viewModel: HomeViewModel) {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_home
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        initAdapter()
        initSwipeToRefresh()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_user_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_toggle_view -> {
                isGridView = !isGridView
                binding.usersRv.layoutManager = if (!isGridView)
                    LinearLayoutManager(this, RecyclerView.VERTICAL, false)
                else StaggeredGridLayoutManager(
                    2,
                    StaggeredGridLayoutManager.VERTICAL
                )
                binding.usersRv.adapter = userAdapter
                userAdapter.setIsGridView(isGridView)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initAdapter() {
        binding.usersRv.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        userAdapter = UserAdapter(isGridView, {
            viewModel.retry()
        }) { view, user ->
            run {
                val intent = Intent(this, UserDetailActivity::class.java)
                intent.putExtra(UserDetailActivity.USER, user)
                intent.putExtra(UserDetailActivity.TRANSITION_NAME, ViewCompat.getTransitionName(view))
                intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT

                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this, view,
                    ViewCompat.getTransitionName(view)!!
                )
                startActivity(intent, options.toBundle())
            }
        }
        binding.usersRv.adapter = userAdapter
        viewModel.getUserPagedList().observe(this, Observer<PagedList<User>> { userAdapter.submitList(it) })
        viewModel.getNetworkState().observe(this, Observer<NetworkState> { userAdapter.setNetworkState(it) })
    }

    private fun initSwipeToRefresh() {
        viewModel.getRefreshState().observe(this, Observer { networkState ->
            if (userAdapter.currentList != null) {
                if (userAdapter.currentList!!.size > 0) {
                    binding.usersSwipeRefreshLayout.isRefreshing = networkState?.status == NetworkState.LOADING.status
                } else {
                    setInitialLoadingState(networkState)
                }
            } else {
                setInitialLoadingState(networkState)
            }
        })
        binding.usersSwipeRefreshLayout.setOnRefreshListener { viewModel.refresh() }
    }

    private fun setInitialLoadingState(networkState: NetworkState?) {
        //error message
        binding.errorLayout.errorMessageTextView.visibility =
            if (networkState?.message != null) View.VISIBLE else View.GONE
        if (networkState?.message != null) {
            binding.errorLayout.errorMessageTextView.text = networkState.message
        }

        //loading and retry
        binding.errorLayout.retryLoadingButton.visibility =
            if (networkState?.status == Status.FAILED) View.VISIBLE else View.GONE
        binding.errorLayout.loadingProgressBar.visibility =
            if (networkState?.status == Status.RUNNING) View.VISIBLE else View.GONE

        binding.usersSwipeRefreshLayout.isEnabled = networkState?.status == Status.SUCCESS
        binding.errorLayout.retryLoadingButton.setOnClickListener { viewModel.retry() }
    }
}
