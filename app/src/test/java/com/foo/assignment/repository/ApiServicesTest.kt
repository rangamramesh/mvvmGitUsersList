package com.foo.assignment.repository

import com.foo.assignment.BaseTest
import com.foo.assignment.data.model.User
import com.foo.assignment.data.remote.ApiServices
import com.foo.assignment.whenever
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

/**
 * Created by srinivas on 2019-07-02.
 */

@RunWith(JUnit4::class)
class ApiServicesTest : BaseTest() {

    lateinit var apiServices: ApiServices

    @Before
    fun setUp() {
        apiServices = Mockito.mock(ApiServices::class.java)
    }


    @Test
    fun testOnSuccess() {
        whenever(apiServices.getUsers(ArgumentMatchers.anyLong(), ArgumentMatchers.anyInt()))
            .thenReturn(Single.just(dummyData()))
        val datalist = apiServices.getUsers(0L, 1)
        datalist.subscribe { it ->
            Assert.assertTrue(dummyData().size == it.size)
        }
    }

    @Test
    fun testOnError() {
        val response = Throwable("Error response")
        whenever(apiServices.getUsers(ArgumentMatchers.anyLong(), ArgumentMatchers.anyInt()))
            .thenReturn(Single.error(response))
        val datalist = apiServices.getUsers(0L, 1)
        datalist.doOnError {
            Assert.assertTrue(it == response)
        }

    }

    @Test
    fun testOnEmpty() {
        whenever(apiServices.getUsers(ArgumentMatchers.anyLong(), ArgumentMatchers.anyInt()))
            .thenReturn(Single.just(emptyList()))
        val datalist = apiServices.getUsers(0L, 1)
        datalist.doOnSuccess {
            Assert.assertTrue(it.isEmpty())
        }
    }

}