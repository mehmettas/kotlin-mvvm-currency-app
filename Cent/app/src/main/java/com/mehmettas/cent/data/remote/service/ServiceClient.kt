package com.mehmettas.cent.data.remote.service

import android.os.Build
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mehmettas.cent.CoreApp
import com.mehmettas.cent.utils.AppConstants.API_URL
import com.mehmettas.cent.utils.AppConstants.REQUEST_TIMEOUT
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit
import android.os.StrictMode



object ServiceClient {
    private var retrofit: Retrofit? = null
    // Create Logger
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val cacheDir = File(CoreApp.context.cacheDir, UUID.randomUUID().toString())
    // 50 MiB cache
    private val cache = Cache(cacheDir, 50 * 1024 * 1024)

    // Create a Custom Interceptor to apply Headers application wide
    private val headerInterceptor = Interceptor { chain ->
        var request = chain.request().newBuilder()
        /*request
            //.addHeader("x-device-type", Build.DEVICE)
            //.addHeader("Accept-Language", Locale.getDefault().language)
            .addHeader("Accept-Language", "tr")
            .addHeader("Accept", "application/json")
            .addHeader("Content-Type","application/x-www-form-urlencoded")*/
        request
            //.addHeader("x-device-type", Build.DEVICE)
            //.addHeader("Accept-Language", Locale.getDefault().language)
            .addHeader("Accept-Language", "tr")
            .addHeader("Accept", "application/json")
            .addHeader("Content-Type","application/json")
       /* if (PrefUtils.isLogged()) {
            request.addHeader("Authorization", "Bearer " + PrefUtils.getCourier().courierToken)
        }*/
        chain.proceed(request.build())
    }

    // Create OkHttp Client
    private val okHttp = OkHttpClient.Builder()
        .cache(cache)
        .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(headerInterceptor)
        .addInterceptor(logger)


    private fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API_URL)
                .client(okHttp.build())
                //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        }
        return retrofit!!
    }

    fun createNetworkClient() = getClient()

    inline fun <reified T> createWebService(): T {
        return createNetworkClient().create(T::class.java)
    }
}