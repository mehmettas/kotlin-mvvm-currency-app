package com.mehmettas.cent.ui.main

import com.mehmettas.cent.data.remote.model.symbol.Symbol
import com.mehmettas.cent.data.remote.network.ResultWrapper
import com.mehmettas.cent.data.repository.DataManager
import com.mehmettas.cent.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(dataManager: DataManager) : BaseViewModel<IMainNavigator>(dataManager){

    fun getCurrencySymbolDetail()
    {
        getNavigator().showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            when(val result: ResultWrapper<Symbol> = withContext(Dispatchers.IO){dataManager.getCurrenciesWithDetail()}){
                is ResultWrapper.Success -> {
                    getNavigator().hideLoading()
                }
                is ResultWrapper.Error -> {
                    getNavigator().hideLoading()
                    getNavigator().onError(result.exception.message)
                }
            }
        }
    }
}