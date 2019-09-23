package com.foo.assignment.di.module

import androidx.lifecycle.ViewModel
import com.foo.assignment.di.ViewModelKey
import com.foo.assignment.ui.detail.UserDetailViewModel
import com.foo.assignment.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Ramesh on 2019-06-28.
 */
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserDetailViewModel::class)
    abstract fun bindUserDetailViewModel(userDetailViewModel: UserDetailViewModel): ViewModel
}