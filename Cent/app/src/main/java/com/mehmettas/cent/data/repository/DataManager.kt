package com.mehmettas.cent.data.repository

import com.mehmettas.cent.data.remote.RemoteDataManager
import com.mehmettas.cent.data.remote.model.rate.RatesResponse
import com.mehmettas.cent.data.remote.model.symbol.Symbol
import com.mehmettas.cent.data.remote.model.symbol.SymbolResponse
import com.mehmettas.cent.data.remote.network.ResultWrapper

class DataManager(
    private val remoteDataManager: RemoteDataManager):IDataManager {

    override suspend fun getCurrenciesWithDetail(): ResultWrapper<SymbolResponse>  =
        remoteDataManager.getCurrenciesWithDetail()

    override suspend fun getLatestRatesAsync(): ResultWrapper<RatesResponse> =
        remoteDataManager.getLatestRatesAsync()


    override suspend fun getRatesOfDateAsync(date: String): ResultWrapper<RatesResponse> =
        remoteDataManager.getRatesOfDateAsync(date)
}

