package com.mehmettas.cent.ui.currencydetail

import com.mehmettas.cent.R
import com.mehmettas.cent.ui.base.BaseActivity
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

    }
}