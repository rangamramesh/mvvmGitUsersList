package com.foo.assignment.repository

import com.foo.assignment.BaseTest
import com.foo.assignment.data.remote.ApiServices
import com.foo.assignment.data.repository.ApiRepository
import com.foo.assignment.data.repository.Status
import com.foo.assignment.whenever
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

/**
 * Created by srinivas on 2019-07-03.
 */
class ApiRepositoryTest : BaseTest() {

    lateinit var apiServices: ApiServices
    lateinit var appRepository: ApiRepository


    @Before
    fun setUp() {
        apiServices = Mockito.mock(ApiServices::class.java)
        appRepository = ApiRepository(apiServices)
    }

}