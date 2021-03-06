package com.mehmettas.cent.ui.currencydetail

import com.mehmettas.cent.R
import com.mehmettas.cent.data.remote.model.symbol.Currency
import com.mehmettas.cent.ui.base.BaseActivity
import com.mehmettas.cent.utils.AppConstants
import kotlinx.android.synthetic.main.activity_currency_detail.*
import kotlinx.android.synthetic.main.activity_currency_detail.textCurrencyValue
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.mehmettas.cent.data.remote.model.rate_time_period.TwoDaysWithBase
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import com.mehmettas.cent.utils.extensions.*


class CurrencyDetailActivity: BaseActivity(), ICurrencyDetailNavigator {
    private val viewModel by viewModel<CurrencyDetailViewModel>()
    lateinit var currency:Currency

    override val layoutId: Int?
        get() = R.layout.activity_currency_detail

    override fun initNavigator() {
       viewModel.setNavigator(this)
    }

    override fun initUI() {
        currency = intent.getSerializableExtra(AppConstants.CURRENCY_INTENT) as Currency
        initData()
    }

    private fun initData()
    {
        when(currency.currencyDraweble)
        {
            AppConstants.PURPLE -> ellipse_logo.setImageResource(R.drawable.ellipse_logo_purple)
            AppConstants.BLUE -> ellipse_logo.setImageResource(R.drawable.ellipse_logo_blue)
            AppConstants.BLACK -> ellipse_logo.setImageResource(R.drawable.ellipse_logo_black)
            AppConstants.ORANGE -> ellipse_logo.setImageResource(R.drawable.ellipse_logo_orange)
        }

        textBase.text = currency.currencyName
        textLogoBaseCode.text = currency.symbol
        textCurrencyValue.text = "%.3f".format(trimForBothSides(currency.rateValue,1,1).toDouble())
        configureUpAndDown(currency.percentDifferenceValue)

        dataChart.setTouchEnabled(true)
        dataChart.setPinchZoom(true)

        initMenuItemBackground(AppConstants.MONTH)
        createXAxisData(AppConstants.MONTH)
        createYAxisData(AppConstants.MONTH)
    }

    override fun initListener() {

        clWeek.setOnClickListener {
            initMenuItemBackground(AppConstants.WEEK)
            createXAxisData(AppConstants.WEEK)
            createYAxisData(AppConstants.WEEK)
        }

        clMonth.setOnClickListener {
            initMenuItemBackground(AppConstants.MONTH)
            createXAxisData(AppConstants.MONTH)
            createYAxisData(AppConstants.MONTH)
        }

        clYear.setOnClickListener {
            initMenuItemBackground(AppConstants.YEAR)
            createXAxisData(AppConstants.YEAR)
            createYAxisData(AppConstants.YEAR)
        }

        clFiveYear.setOnClickListener {
            initMenuItemBackground(AppConstants.FIVE_YEAR)
            createXAxisData(AppConstants.FIVE_YEAR)
            createYAxisData(AppConstants.FIVE_YEAR)
        }

        imgBack.setOnClickListener {
            super.onBackPressed()
        }

    }

    private fun initMenuItemBackground(itemType:String)
    {
        when(itemType)
        {
            AppConstants.WEEK -> {
                clWeek.setBackgroundResource(R.drawable.img_menu_selected)
                clMonth.setBackgroundResource(0)
                clYear.setBackgroundResource(0)
                clFiveYear.setBackgroundResource(0)
            }
            AppConstants.MONTH -> {
                clMonth.setBackgroundResource(R.drawable.img_menu_selected)
                clYear.setBackgroundResource(0)
                clFiveYear.setBackgroundResource(0)
                clWeek.setBackgroundResource(0)
            }
            AppConstants.YEAR -> {
                clYear.setBackgroundResource(R.drawable.img_menu_selected)
                clMonth.setBackgroundResource(0)
                clFiveYear.setBackgroundResource(0)
                clWeek.setBackgroundResource(0)
            }
            AppConstants.FIVE_YEAR -> {
                clFiveYear.setBackgroundResource(R.drawable.img_menu_selected)
                clYear.setBackgroundResource(0)
                clMonth.setBackgroundResource(0)
                clWeek.setBackgroundResource(0)
            }
        }
    }

    private fun createXAxisData(type:String): ArrayList<String>
    {
        var xAxisData = arrayListOf<String>()
        when(type)
        {
            AppConstants.WEEK -> {
                xAxisData = getDaysOftheWeek()
            }
        }
        return xAxisData
    }

    private fun createYAxisData(type:String)
    {
        var firstDate  = ""
        var currentDate = getCurrentDate()
        when(type)
        {
            AppConstants.WEEK -> {
                firstDate = getDateOfDaysAgo(7)
            }
            AppConstants.MONTH -> {
                firstDate = getDateOfDaysAgo(31)
            }
            AppConstants.YEAR -> {
                firstDate = getDateOfDaysAgo(365)
            }
            AppConstants.FIVE_YEAR -> {
                firstDate = getDateOfDaysAgo(1825)
            }
        }
        viewModel.getRatesBetweenTwoTimeAsync(currentDate,firstDate,currency.code,intent.getStringExtra(AppConstants.BASE))
    }

    private fun configureUpAndDown(upAndDownPercent:String?)
    {
        if (upAndDownPercent?.toDouble()!! <0)
        {
            textRisingDecreasing.setTextColor(resources.getColor(R.color.decreasing_color))
            textRisingDecreasing.text = "- ${upAndDownPercent?.toDouble()?.times(-1)}%"
        }
        else
        {
            textRisingDecreasing.setTextColor(resources.getColor(R.color.rising_color))
            textRisingDecreasing.text = "+ ${upAndDownPercent?.toDouble()}%"
        }
    }

    override fun twoTimePeriodSuccess(data: TwoDaysWithBase?) {
        // Because of the reason that we have complex api with non-key values and dynamic
        // data sets, here I converted LinkedTreeMap into Json object and get all the key values
        // that I need to get

        val days:ArrayList<String> = arrayListOf()
        val values:ArrayList<String> = arrayListOf()
        val treeMap =  data?.rates as LinkedTreeMap<*, *>
        val objectMap = Gson().toJsonTree(treeMap).getAsJsonObject()
        val iterator = objectMap.keySet().iterator()

        while (iterator.hasNext()) {
            days.add(iterator.next() as String)
        }

        for(x in 0 until days.size)
        {
            values.add( (treeMap[days[x]].toString().
                replace("${currency.code}=","").
                replace("{","").
                replace("}","")))
        }

        drawChart(dataChart,values)
    }
}