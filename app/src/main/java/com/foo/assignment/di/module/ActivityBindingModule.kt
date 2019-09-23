package com.foo.assignment.di.module

import com.foo.assignment.ui.detail.UserDetailActivity
import com.foo.assignment.ui.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Ramesh on 2019-06-28.
 */

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun contributeUserDetailActivity(): UserDetailActivity
}