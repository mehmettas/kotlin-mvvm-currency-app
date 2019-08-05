package com.mehmettas.cent.ui.currencybase

import com.mehmettas.cent.R
import com.mehmettas.cent.ui.base.BaseDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.layout_base_selection.*

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
        get() = R.layout.layout_base_selection

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
            dialog.window.setLayout(700, 1000)
            dialog.window.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_dialog))
        }

    }

    override fun initListener() {
        imgClose.setOnClickListener {
            dialog.dismiss()
        }
    }
}