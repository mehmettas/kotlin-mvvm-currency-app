package com.mehmettas.cent.data.remote.service

import com.mehmettas.cent.data.remote.model.base.BaseResponse
import com.mehmettas.cent.data.remote.model.rate.Rates
import com.mehmettas.cent.data.remote.model.rate.RatesResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface IRatesService {
    @GET("latest")
    fun getLatestRatesAsync(): Deferred<Response<RatesResponse>>

}