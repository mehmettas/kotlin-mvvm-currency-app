package com.mehmettas.cent.data.remote.service

import com.mehmettas.cent.data.remote.model.symbol.Symbol
import com.mehmettas.cent.utils.AppConstants
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ICurrencyModelService {

    @GET(AppConstants.API_URL_MODEL)
    fun getCurrenciesWithDetail(): Deferred<Response<Symbol>>

}