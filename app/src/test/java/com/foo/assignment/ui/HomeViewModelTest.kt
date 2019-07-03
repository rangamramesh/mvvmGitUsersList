package com.foo.assignment.ui

import com.foo.assignment.BaseTest
import com.foo.assignment.data.remote.ApiServices
import com.foo.assignment.data.repository.ApiRepository
import com.foo.assignment.ui.home.HomeViewModel
import com.foo.assignment.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.doNothing


/**
 * Created by srinivas on 2019-07-03.
 */
class HomeViewModelTest : BaseTest() {

    private lateinit var apiServices: ApiServices
    private lateinit var appRepository: ApiRepository
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        apiServices = Mockito.mock(ApiServices::class.java)
        appRepository = Mockito.mock(ApiRepository::class.java)
        homeViewModel = HomeViewModel(appRepository)
    }


    @Test
    fun testRefresh() {
        whenever(apiServices.getUsers(ArgumentMatchers.anyLong(), ArgumentMatchers.anyInt()))
            .thenReturn(Single.just(dummyData()))

        doNothing().`when`(appRepository).refresh()

        homeViewModel.refresh()

        Mockito.verify(appRepository).refresh()
    }

    @Test
    fun testRetry() {
        whenever(apiServices.getUsers(ArgumentMatchers.anyLong(), ArgumentMatchers.anyInt()))
            .thenReturn(Single.just(dummyData()))

        doNothing().`when`(appRepository).retry()

        homeViewModel.retry()

        Mockito.verify(appRepository).retry()
    }

}