package com.foo.assignment.di.module

import com.foo.assignment.BuildConfig
import com.foo.assignment.data.remote.ApiServices
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by srinivas on 2019-06-28.
 */

@Module
class NetworkModule {

    @Module
    companion object {
        @JvmStatic
        @Singleton
        @Provides
        fun provideApiServices(@Named("base_retrofit") retrofit: Retrofit): ApiServices {
            return retrofit.create(ApiServices::class.java)
        }

        @JvmStatic
        @Singleton
        @Provides
        fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

        @JvmStatic
        @Singleton
        @Provides
        fun provideOkHttp(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
            OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build()

        @Singleton
        @JvmStatic
        @Provides
        @Named("base_retrofit")
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()

    }

}