package com.mehmettas.cent.ui.currencydetail

import com.mehmettas.cent.data.remote.model.rate_time_period.TwoDaysWithBase
import com.mehmettas.cent.ui.base.IBaseNavigator

interface ICurrencyDetailNavigator: IBaseNavigator {
    fun twoTimePeriodSuccess(data:TwoDaysWithBase?)
}