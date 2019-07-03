package com.foo.assignment.app

import com.foo.assignment.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Created by srinivas on 2019-06-28.
 */
class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component = DaggerAppComponent.builder()
            .app(this)
            .build()
        component.inject(this)
        return component
    }
}