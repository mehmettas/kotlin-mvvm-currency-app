package com.mehmettas.cent.ui.currencydetail

import com.mehmettas.cent.R
import com.mehmettas.cent.data.remote.model.symbol.Currency
import com.mehmettas.cent.ui.base.BaseActivity
import com.mehmettas.cent.utils.AppConstants
import com.mehmettas.cent.utils.extensions.trimForBothSides
import kotlinx.android.synthetic.main.activity_currency_detail.*
import kotlinx.android.synthetic.main.activity_currency_detail.textCurrencyValue
import kotlinx.android.synthetic.main.layout_item_currency.*
import kotlinx.android.synthetic.main.layout_item_currency.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

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

        textBase.text = currency.code
        textLogoBaseCode.text = currency.symbol
        textCurrencyValue.text = "%.3f".format(trimForBothSides(currency.rateValue,1,1).toDouble())
        configureUpAndDown(currency.percentDifferenceValue)
    }

    override fun initListener() {

        clWeek.setOnClickListener {
           initMenuItemBackground(AppConstants.WEEK)
        }

        clMonth.setOnClickListener {
            initMenuItemBackground(AppConstants.MONTH)
        }

        clYear.setOnClickListener {
            initMenuItemBackground(AppConstants.YEAR)
        }

        clFiveYear.setOnClickListener {
            initMenuItemBackground(AppConstants.FIVE_YEAR)
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
}