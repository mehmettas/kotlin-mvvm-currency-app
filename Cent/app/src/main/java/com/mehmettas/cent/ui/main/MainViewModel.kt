package com.mehmettas.cent.ui.main

import androidx.lifecycle.MutableLiveData
import com.mehmettas.cent.data.remote.model.rate.RatesResponse
import com.mehmettas.cent.data.remote.model.symbol.SymbolResponse
import com.mehmettas.cent.data.remote.network.ResultWrapper
import com.mehmettas.cent.data.repository.DataManager
import com.mehmettas.cent.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(dataManager: DataManager) : BaseViewModel<IMainNavigator>(dataManager){
    val latestRates:MutableLiveData<RatesResponse> = MutableLiveData()
    val latestRatesWithDate:MutableLiveData<RatesResponse> = MutableLiveData()

    fun getCurrencySymbolDetail()
    {
        getNavigator().showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            when(val result: ResultWrapper<SymbolResponse> = withContext(Dispatchers.IO){dataManager.getCurrenciesWithDetail()}){
                is ResultWrapper.Success -> {
                    getNavigator().hideLoading()
                    getNavigator().currencyDetailSuccess(result.data.response)
                }
                is ResultWrapper.Error -> {
                    getNavigator().hideLoading()
                    getNavigator().onError(result.exception.message)
                }
            }
        }
    }

    fun getLatestRatesAsync()
    {
        getNavigator().showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            when(val result: ResultWrapper<RatesResponse> = withContext(Dispatchers.IO){dataManager.getLatestRatesAsync()}){
                is ResultWrapper.Success -> {
                    getNavigator().hideLoading()
                    latestRates.value = result.data
                }
                is ResultWrapper.Error -> {
                    getNavigator().hideLoading()
                    getNavigator().onError(result.exception.message)
                }
            }
        }
    }

    fun getRatesWithDateAsync(date:String)
    {
        getNavigator().showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            when(val result: ResultWrapper<RatesResponse> = withContext(Dispatchers.IO){dataManager.getRatesOfDateAsync(date)}){
                is ResultWrapper.Success -> {
                    getNavigator().hideLoading()
                    latestRatesWithDate.value = result.data
                }
                is ResultWrapper.Error -> {
                    getNavigator().hideLoading()
                    getNavigator().onError(result.exception.message)
                }
            }
        }
    }

    fun getLatestRatesWithBaseAsync(baseCurrencyCode:String)
    {
        getNavigator().showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            when(val result: ResultWrapper<RatesResponse> = withContext(Dispatchers.IO){dataManager.getLatestWithBaseAsync(baseCurrencyCode)}){
                is ResultWrapper.Success -> {
                    getNavigator().hideLoading()
                    getNavigator().latestWithBaseSuccess(result.data)
                }
                is ResultWrapper.Error -> {
                    getNavigator().hideLoading()
                    getNavigator().onError(result.exception.message)
                }
            }
        }
    }

    fun getRatesWithBaseAndDateAsync(date:String,base:String)
    {
        getNavigator().showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            when(val result: ResultWrapper<RatesResponse> = withContext(Dispatchers.IO){dataManager.getRatesOfDateWithBaseAsync(date,base)}){
                is ResultWrapper.Success -> {
                    getNavigator().hideLoading()
                    latestRatesWithDate.value = result.data
                }
                is ResultWrapper.Error -> {
                    getNavigator().hideLoading()
                    getNavigator().onError(result.exception.message)
                }
            }
        }
    }


}