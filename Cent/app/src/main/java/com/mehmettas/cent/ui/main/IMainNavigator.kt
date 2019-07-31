package com.mehmettas.cent.ui.main

import com.mehmettas.cent.data.remote.model.symbol.Symbol
import com.mehmettas.cent.ui.base.IBaseNavigator

interface IMainNavigator: IBaseNavigator {
    fun currencyDetailSuccess(data:Symbol)
}