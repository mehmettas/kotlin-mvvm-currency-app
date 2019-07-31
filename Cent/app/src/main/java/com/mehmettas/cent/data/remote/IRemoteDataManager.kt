package com.mehmettas.cent.data.remote

import com.mehmettas.cent.data.remote.model.symbol.Symbol
import com.mehmettas.cent.data.remote.network.ResultWrapper

interface IRemoteDataManager {
    suspend fun getCurrenciesWithDetail() : ResultWrapper<Symbol>
}