package com.foo.assignment.di.component

import android.app.Application
import com.foo.assignment.di.module.ActivityBindingModule
import com.foo.assignment.di.module.AppModule
import com.foo.assignment.di.module.NetworkModule
import com.foo.assignment.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by srinivas on 2019-06-28.
 */
@Singleton
@Component(
    modules = [NetworkModule::class, AppModule::class, ViewModelModule::class,
        ActivityBindingModule::class, AndroidSupportInjectionModule::class]
)
interface AppComponent : AndroidInjector<DaggerApplication> {
    fun inject(application: Application)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(application: Application): Builder

        fun build(): AppComponent
    }
}