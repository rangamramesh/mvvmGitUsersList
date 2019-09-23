package com.foo.assignment.data.remote

import com.foo.assignment.data.model.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Ramesh on 2019-06-28.
 */
interface ApiServices {

    @GET("users")
    fun getUsers(@Query("since") since: Long, @Query("per_page") perPage: Int)
            : Single<List<User>>
}