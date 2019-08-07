package com.mehmettas.cent.ui.main

import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.mehmettas.cent.R
import com.mehmettas.cent.data.remote.model.rate.RatesResponse
import com.mehmettas.cent.data.remote.model.symbol.Symbol
import com.mehmettas.cent.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.google.gson.JsonParser
import com.mehmettas.cent.data.remote.model.symbol.Currency
import com.mehmettas.cent.ui.currencybase.CurrencyBaseDialog
import com.mehmettas.cent.ui.currencydetail.CurrencyDetailActivity
import com.mehmettas.cent.ui.main.MainAdapter.MainAdapter
import com.mehmettas.cent.utils.AppConstants
import com.mehmettas.cent.utils.extensions.getDateOfDaysAgo
import com.mehmettas.cent.utils.extensions.launchActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: BaseActivity(), IMainNavigator,MainAdapter.MainListListener, CurrencyBaseDialog.DialogReturnedBackListener {
    private val viewModel by viewModel<MainViewModel>() // inject the viewModel

    private val currencyAdapter by lazy {
        MainAdapter(arrayListOf(), this)
    }

    lateinit var symbolData:Symbol
    lateinit var latestRates:RatesResponse
    lateinit var latestRatesWithDate:RatesResponse

    private var allCurrencies:ArrayList<Currency> = arrayListOf()
    private var rateCodes:ArrayList<String> = arrayListOf()

    override val layoutId: Int?
        get() = R.layout.activity_main

    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    override fun initUI() {
        observeViewModel()
        rvMainList.setHasFixedSize(true)
        rvMainList.adapter = currencyAdapter
        viewModel.getCurrencySymbolDetail()
    }

    override fun initListener() {
        icBase.setOnClickListener {
            CurrencyBaseDialog.newInstance(allCurrencies) // create new instance with the arguments to be our model class.
                .show(supportFragmentManager.beginTransaction(),"")
        }

    }

    private fun observeViewModel() // Observe the data that we requested
    {
        viewModel.latestRates.observe(this, Observer {
            latestRates = it
            textCurrentBase.text = latestRates.base
            formSymbolApi(latestRates,true)
            viewModel.getRatesWithDateAsync(getDateOfDaysAgo(3))
        })

        viewModel.latestRatesWithDate.observe(this, Observer {
            latestRatesWithDate = it
            formSymbolApi(latestRatesWithDate,false)
        })
    }

    // Because of the format of json data (has non-key values), I iterated
    // through string and reform it as JSONObject
    private fun formSymbolApi(latestRates:RatesResponse,isMainControl:Boolean)
    {
        val parser = JsonParser()
        val ratesObject = parser.parse(Gson().toJson(latestRates.data)).asJsonObject
        val iterator = ratesObject.keySet().iterator()

        while (iterator.hasNext()) {
            rateCodes.add(iterator.next() as String)
        }

        if (isMainControl)
            createAllCurrencies(rateCodes,ratesObject)
        else
            appendYesterdayValues(rateCodes,ratesObject)
    }

    private fun createAllCurrencies(rateCodes:ArrayList<String>,ratesObject: JsonObject)
    {
        for(i in 0 until rateCodes.size-1)
        {
            val currency = symbolData.currencies.find {
                it.code == rateCodes[i]
            }
            if (!currency?.code.isNullOrEmpty())
            {
                currency?.rateValue = ratesObject[currency?.code].toString()
                allCurrencies.add(currency!!)
            }
        }
    }

    private fun appendYesterdayValues(rateCodes: ArrayList<String>, ratesObject: JsonObject)
    {
        for(i in 0 until rateCodes.size-1)
        {
            val currency = symbolData.currencies.find {
                it.code == rateCodes[i]
            }
            if (!currency?.code.isNullOrEmpty())
            {
                currency?.previousDayValue = ratesObject[currency?.code].toString()
                allCurrencies.find {
                    it.code == currency?.code
                }?.previousDayValue = ratesObject[currency?.code].toString()
            }
        }
        currencyAdapter.addData(allCurrencies)
    }

    override fun currencyDetailSuccess(data: Symbol) { // Implement the method which is executed in success case
        symbolData = data
        viewModel.getLatestRatesAsync()
    }

    override fun onItemSelectedListener(currency: Currency) {
        launchActivity<CurrencyDetailActivity> {
            putExtra(AppConstants.CURRENCY_INTENT,currency)
            putExtra(AppConstants.BASE,textCurrentBase.text)
        }  // Use Intent extension to easily use intents .
    }

    override fun whenDialogComplete(selectedBase: String) {
        textCurrentBase.text = selectedBase
        viewModel.getLatestRatesWithBaseAsync(selectedBase)
    }

    override fun latestWithBaseSuccess(data: RatesResponse) {
        allCurrencies = arrayListOf()
        rateCodes = arrayListOf()
        formSymbolApi(data,true)
        viewModel.getRatesWithBaseAndDateAsync(getDateOfDaysAgo(1),data.base)
    }
}
