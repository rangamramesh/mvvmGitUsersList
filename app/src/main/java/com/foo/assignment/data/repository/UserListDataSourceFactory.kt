package com.foo.assignment.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.foo.assignment.data.model.User
import com.foo.assignment.data.remote.ApiServices
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Ramesh on 2019-07-01.
 */

class UserListDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val apiServices: ApiServices
) : DataSource.Factory<Long, User>() {

    val usersDataSourceLiveData = MutableLiveData<UserListDataSource>()

    override fun create(): DataSource<Long, User> {
        val usersDataSource = UserListDataSource(apiServices, compositeDisposable)
        usersDataSourceLiveData.postValue(usersDataSource)
        return usersDataSource
    }

}