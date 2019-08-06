package com.mehmettas.cent.ui.currencydetail

import com.mehmettas.cent.R
import com.mehmettas.cent.ui.base.BaseActivity
import com.mehmettas.cent.utils.AppConstants
import kotlinx.android.synthetic.main.activity_currency_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrencyDetailActivity: BaseActivity(), ICurrencyDetailNavigator {
    private val viewModel by viewModel<CurrencyDetailViewModel>()

    override val layoutId: Int?
        get() = R.layout.activity_currency_detail

    override fun initNavigator() {
       viewModel.setNavigator(this)
    }

    override fun initUI() {

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

    }

    private fun initMenuItemBackground(itemType:String)
    {
        when(itemType)
        {
            AppConstants.WEEK -> clWeek.setBackgroundResource(R.drawable.img_menu_selected)
            AppConstants.MONTH -> clMonth.setBackgroundResource(R.drawable.img_menu_selected)
            AppConstants.YEAR -> clYear.setBackgroundResource(R.drawable.img_menu_selected)
            AppConstants.FIVE_YEAR -> clFiveYear.setBackgroundResource(R.drawable.img_menu_selected)
        }

    }
}