package com.mehmettas.cent.ui.currencydetail

import com.mehmettas.cent.data.remote.model.rate_time_period.TwoDaysWithBase
import com.mehmettas.cent.data.remote.network.ResultWrapper
import com.mehmettas.cent.data.repository.DataManager
import com.mehmettas.cent.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CurrencyDetailViewModel(dataManager:DataManager): BaseViewModel<ICurrencyDetailNavigator>(dataManager) {

    fun getRatesBetweenTwoTimeAsync(endDate:String,
                                    startDate:String,
                                    symbol:String,
                                    base:String)
    {
        GlobalScope.launch(Dispatchers.Main) {
            when(val result: ResultWrapper<TwoDaysWithBase> = withContext(Dispatchers.IO){dataManager.getRatesBetweenTwoTimeAsync(
                endDate,startDate,symbol,base
            )}){
                is ResultWrapper.Success -> {
                    getNavigator().twoTimePeriodSuccess(result.data)
                }
                is ResultWrapper.Error -> {
                    getNavigator().onError(result.exception.message)
                }
            }
        }
    }

}