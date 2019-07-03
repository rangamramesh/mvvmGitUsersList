package com.foo.assignment

import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing

/**
 * Created by srinivas on 2019-07-02.
 */

inline fun <reified T> mock() = Mockito.mock(T::class.java)
fun <T> whenever(methodCall: T) : OngoingStubbing<T> = Mockito.`when`(methodCall)