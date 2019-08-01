package com.mehmettas.cent.data.remote

import com.mehmettas.cent.data.remote.model.rate.Rates
import com.mehmettas.cent.data.remote.model.rate.RatesResponse
import com.mehmettas.cent.data.remote.model.symbol.SymbolResponse
import com.mehmettas.cent.data.remote.network.RemoteDataException
import com.mehmettas.cent.data.remote.network.ResultWrapper
import com.mehmettas.cent.data.remote.service.ICurrencyModelService
import com.mehmettas.cent.data.remote.service.IRatesService
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class RemoteDataManager(
    private val modelService:ICurrencyModelService,
    private val ratesService:IRatesService
    ): IRemoteDataManager {

    override suspend fun getCurrenciesWithDetail(): ResultWrapper<SymbolResponse> =
        withContext(Dispatchers.IO) {
            resultWrapper(modelService.getCurrenciesWithDetail())
        }

    override suspend fun getLatestRatesAsync(): ResultWrapper<RatesResponse> =
        withContext(Dispatchers.IO) {
            resultWrapper(ratesService.getLatestRatesAsync())
        }

    private suspend inline fun <reified T : Any> resultWrapper(request: Deferred<Response<T>>): ResultWrapper<T> {
        return try {
            val response = request.await()
            if (response.isSuccessful) {
                ResultWrapper.Success(response.body()!!)
            } else {
                ResultWrapper.Error(RemoteDataException(response.errorBody()))
            }
        } catch (ex: Throwable) {
            ResultWrapper.Error(RemoteDataException(ex))
        }
    }

}