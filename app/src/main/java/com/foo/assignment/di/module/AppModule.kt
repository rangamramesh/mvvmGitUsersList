package com.foo.assignment.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

/**
 * Created by srinivas on 2019-06-28.
 */
@Module
abstract class AppModule {
    @Binds
    abstract fun provideContext(application: Application): Context
}