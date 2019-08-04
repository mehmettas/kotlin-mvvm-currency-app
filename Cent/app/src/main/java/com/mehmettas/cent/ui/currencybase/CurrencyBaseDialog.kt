package com.mehmettas.cent.ui.currencybase

import com.mehmettas.cent.R
import com.mehmettas.cent.ui.base.BaseDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrencyBaseDialog: BaseDialogFragment(), ICurrencyBaseNavigator {
    private val viewModel by viewModel<CurrencyBaseViewModel>()

    companion object {
        fun newInstance(): CurrencyBaseDialog {
            return CurrencyBaseDialog().apply {
                setStyle(STYLE_NORMAL, R.style.DialogFragmentTheme)
            }
        }
    }

    override val layoutId: Int
        get() = R.layout.dialog_layout

    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    override fun initUI() {
        observeViewModel()
    }

    private fun observeViewModel() {

    }

    override fun onStart() {
        super.onStart()

    }

    override fun initListener() {
    }
}