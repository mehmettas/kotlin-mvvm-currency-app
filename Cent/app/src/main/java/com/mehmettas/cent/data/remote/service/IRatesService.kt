package com.mehmettas.cent.data.remote.service

import com.mehmettas.cent.data.remote.model.rate.RatesResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IRatesService {

    @GET("latest")
    fun getLatestRatesAsync(): Deferred<Response<RatesResponse>>

    @GET("{date}")
    fun getRatesOfDateAsync(@Path("date")date:String): Deferred<Response<RatesResponse>>

    @GET("latest")
    fun getLatestWithBaseAsync(@Query("base") baseCurrency:String): Deferred<Response<RatesResponse>>

    @GET("{date}")
    fun getRatesOfDateWithBaseAsync(
        @Path("date")date:String,
        @Query("base")baseCurrency: String
    ): Deferred<Response<RatesResponse>>

}