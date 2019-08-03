package com.mehmettas.cent.data.remote.service

import com.mehmettas.cent.data.remote.model.rate.RatesResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Part

interface IRatesService {

    @GET("latest")
    fun getLatestRatesAsync(): Deferred<Response<RatesResponse>>

    @GET("{date}")
    fun getRatesOfDateAsync(@Part("date")date:String): Deferred<Response<RatesResponse>>

}