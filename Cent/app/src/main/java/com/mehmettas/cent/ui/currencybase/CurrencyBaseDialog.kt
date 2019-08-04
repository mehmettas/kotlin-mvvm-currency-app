package com.mehmettas.cent.ui.currencybase

import com.mehmettas.cent.R
import com.mehmettas.cent.ui.base.BaseDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.view.ViewGroup

class CurrencyBaseDialog: BaseDialogFragment(), ICurrencyBaseNavigator {
    private val viewModel by viewModel<CurrencyBaseViewModel>()

    companion object {
        fun newInstance(): CurrencyBaseDialog {
            return CurrencyBaseDialog().apply {
                setStyle(STYLE_NORMAL
                    , R.style.FullScreenDialogStyle)
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
        var dialog = dialog
        if (dialog!=null)
        {
            val width = ViewGroup.LayoutParams.WRAP_CONTENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window.setLayout(width, height)
        }

    }

    override fun initListener() {
    }
}