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

    override fun initListener() {

    }

    private fun observeViewModel() // Observe the data that we requested
    {
        viewModel.latestRates.observe(this, Observer {
            latestRates = it
            mergeBothApis()
        })
    }

    private fun mergeBothApis()
    {
        var data = latestRates.data


        val parser = JsonParser()
        val obj = parser.parse(Gson().toJson(data)).asJsonObject

        val jsonObject = obj

        val iterator = jsonObject.keySet().iterator()
        while (iterator.hasNext()) {
            val key = iterator.next() as String
            println(jsonObject.get(key))
        }

        val jsonObsject = obj as JSONObject


    }

    override fun currencyDetailSuccess(data: Symbol) { // Implement the method which is executed in success case
        symbolData = data
        viewModel.getLatestRatesAsync()
    }
}
