package com.foo.assignment.ui.base

import androidx.lifecycle.ViewModel

/**
 * Created by srinivas on 2019-06-29.
 */
open class BaseViewModel() : ViewModel() {

    override fun onCleared() {
        super.onCleared()
    }
}