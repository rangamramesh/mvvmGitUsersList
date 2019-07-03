package com.foo.assignment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.foo.assignment.data.model.User
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Rule

/**
 * Created by srinivas on 2019-07-02.
 */
open class BaseTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    companion object {

        @BeforeClass
        fun before() {
            RxAndroidPlugins.reset()
            RxJavaPlugins.reset()
            RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        }

        @AfterClass
        fun after() {
            RxAndroidPlugins.reset()
            RxJavaPlugins.reset()
        }
    }

    fun dummyData(): List<User> {
        val data = ArrayList<User>()
        data.add(User())
        data.add(User())
        data.add(User())
        return data
    }
}