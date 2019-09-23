package com.foo.assignment.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

/**
 * Created by Ramesh on 2019-07-02.
 */

class PagedListWrapper<T> constructor(
    private val pagedList: LiveData<PagedList<T>>,
    private val networkState: LiveData<NetworkState>,
    private val refreshState: LiveData<NetworkState>
) {

    fun getPagedList(): LiveData<PagedList<T>> {
        return pagedList
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return networkState
    }

    fun getRefreshState(): LiveData<NetworkState> {
        return refreshState
    }

}
