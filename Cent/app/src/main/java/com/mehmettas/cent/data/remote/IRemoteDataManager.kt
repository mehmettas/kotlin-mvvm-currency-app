package com.mehmettas.cent.data.remote

import com.mehmettas.cent.data.remote.model.rate.RatesResponse
import com.mehmettas.cent.data.remote.model.rate_time_period.TwoDaysWithBase
import com.mehmettas.cent.data.remote.model.symbol.SymbolResponse
import com.mehmettas.cent.data.remote.network.ResultWrapper

interface IRemoteDataManager {
    suspend fun getCurrenciesWithDetail() : ResultWrapper<SymbolResponse>
    suspend fun getLatestRatesAsync(): ResultWrapper<RatesResponse>
    suspend fun getRatesOfDateAsync(date:String): ResultWrapper<RatesResponse>
    suspend fun getLatestWithBaseAsync(baseCode:String): ResultWrapper<RatesResponse>
    suspend fun getRatesOfDateWithBaseAsync(date: String,baseCode: String): ResultWrapper<RatesResponse>
    suspend fun getRatesBetweenTwoTimeAsync(endAt:String,startAt:String,symbol:String,base:String): ResultWrapper<TwoDaysWithBase>
}