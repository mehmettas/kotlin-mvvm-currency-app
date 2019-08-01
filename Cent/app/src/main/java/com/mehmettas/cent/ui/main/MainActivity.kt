package com.mehmettas.cent.ui.main

import androidx.lifecycle.Observer
import com.mehmettas.cent.R
import com.mehmettas.cent.data.remote.model.rate.RatesResponse
import com.mehmettas.cent.data.remote.model.symbol.Symbol
import com.mehmettas.cent.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity: BaseActivity(), IMainNavigator {
    private val viewModel by viewModel<MainViewModel>() // inject the viewModel
    lateinit var symbolData:Symbol
    lateinit var latestRates:RatesResponse

    override val layoutId: Int?
        get() = R.layout.activity_main

    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    override fun initUI() {
        observeViewModel()
        viewModel.getCurrencySymbolDetail()
    }

    private fun observeViewModel()
    {
        viewModel.latestRates.observe(this, Observer {
            latestRates = it
        })
    }

    override fun initListener() {

    }

    override fun currencyDetailSuccess(data: Symbol) {
        symbolData = data
        viewModel.getLatestRatesAsync()
    }
}
