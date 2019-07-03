package com.foo.assignment.ui.detail

import com.foo.assignment.data.repository.ApiRepository
import com.foo.assignment.ui.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by srinivas on 2019-07-01.
 */
class UserDetailViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : BaseViewModel() {

}