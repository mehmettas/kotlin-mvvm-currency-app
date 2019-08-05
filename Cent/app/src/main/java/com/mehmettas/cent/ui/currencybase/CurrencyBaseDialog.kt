package com.mehmettas.cent.ui.currencybase

import android.os.Bundle
import com.mehmettas.cent.R
import com.mehmettas.cent.data.remote.model.symbol.Currency
import com.mehmettas.cent.ui.base.BaseDialogFragment
import com.mehmettas.cent.ui.currencybase.currencybaseadapter.CurrencyBaseAdapter
import com.mehmettas.cent.ui.main.MainAdapter.MainAdapter
import com.mehmettas.cent.utils.AppConstants
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.layout_base_selection.*
import kotlin.collections.ArrayList

class CurrencyBaseDialog: BaseDialogFragment(), ICurrencyBaseNavigator, CurrencyBaseAdapter.BaseAdapterListener {
    private val viewModel by viewModel<CurrencyBaseViewModel>()
    lateinit var currencies:ArrayList<Currency>

    private val currenciesAdapter by lazy {
        CurrencyBaseAdapter(arrayListOf(), this)
    }

    companion object {
        fun newInstance(currencies:ArrayList<Currency>): CurrencyBaseDialog {
            return CurrencyBaseDialog().apply {
                setStyle(STYLE_NORMAL
                    , R.style.FullScreenDialogStyle)
                arguments = Bundle().apply { putSerializable(AppConstants.ALL_CURRENCIES,currencies) }
            }
        }
    }

    override val layoutId: Int
        get() = R.layout.layout_base_selection

    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    override fun initUI() {
        observeViewModel()

        rvBaseSelection.setHasFixedSize(true)
        rvBaseSelection.adapter = currenciesAdapter
        arguments?.let {
            currencies = it.getSerializable(AppConstants.ALL_CURRENCIES) as ArrayList<Currency> // get arguments here.
            currenciesAdapter.addData(currencies)

        }
    }

    private fun observeViewModel() {
    }

    override fun onStart() {
        super.onStart()
        var dialog = dialog
        if (dialog!=null)
        {
            dialog.window.setLayout(750, 1000)
            dialog.window.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_dialog))
        }
    }

    override fun initListener() {
        imgClose.setOnClickListener {
            dialog.dismiss()
        }
    }

    override fun onItemSelected(currency: Currency) {
    }
}