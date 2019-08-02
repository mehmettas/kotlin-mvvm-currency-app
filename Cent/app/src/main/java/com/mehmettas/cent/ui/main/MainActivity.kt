package com.mehmettas.cent.ui.main

import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.mehmettas.cent.R
import com.mehmettas.cent.data.remote.model.rate.RatesResponse
import com.mehmettas.cent.data.remote.model.symbol.Symbol
import com.mehmettas.cent.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.json.JSONObject
import com.google.gson.JsonParser
import com.mehmettas.cent.data.remote.model.symbol.Currency


class MainActivity: BaseActivity(), IMainNavigator {
    private val viewModel by viewModel<MainViewModel>() // inject the viewModel

    lateinit var symbolData:Symbol
    lateinit var latestRates:RatesResponse
    private var allCurrencies:ArrayList<Currency?> = arrayListOf()
    private var rateCodes:ArrayList<String> = arrayListOf()

    override val layoutId: Int?
        get() = R.layout.activity_main

    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    override fun initUI() {
        observeViewModel()
        viewModel.getCurrencySymbolDetail()
    }

    override fun initListener() {

    }

    private fun observeViewModel() // Observe the data that we requested
    {
        viewModel.latestRates.observe(this, Observer {
            latestRates = it
            formSymbolApi()
        })
    }


    // Because of the format of json data (has non-key values), I iterated
    // through string and reform it as JSONObject
    private fun formSymbolApi()
    {
        val parser = JsonParser()
        val ratesObject = parser.parse(Gson().toJson(latestRates.data)).asJsonObject
        val iterator = ratesObject.keySet().iterator()

        while (iterator.hasNext()) {
            rateCodes.add(iterator.next() as String)
        }
        createAllCurrencies(ratesObject)
    }

    private fun createAllCurrencies(ratesObject: JsonObject)
    {
        for(i in 0..rateCodes.size-1)
        {
            val currency = symbolData.currencies.find {
                it.code == rateCodes[i]
            }
            if (!currency?.code.isNullOrEmpty())
            {
                currency?.rateValue = ratesObject[currency?.code].toString()
                allCurrencies.add(currency)
            }
        }
    }

    override fun currencyDetailSuccess(data: Symbol) { // Implement the method which is executed in success case
        symbolData = data
        viewModel.getLatestRatesAsync()
    }
}
