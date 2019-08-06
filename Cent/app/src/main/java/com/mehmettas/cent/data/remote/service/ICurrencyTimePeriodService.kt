package com.mehmettas.cent.data.remote.service

import com.mehmettas.cent.data.remote.model.rate_time_period.TwoDaysWithBase
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ICurrencyTimePeriodService {
    @GET("history")
    fun getRatesBetweenTwoTimeAsync(@Query("end_at")endAt:String,
                                    @Query("start_at")startAt:String,
                                    @Query("symbols")symbols:String,
                                    @Query("base")base:String): Deferred<Response<TwoDaysWithBase>>
}