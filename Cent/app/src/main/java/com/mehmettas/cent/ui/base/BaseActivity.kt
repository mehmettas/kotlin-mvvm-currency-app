package com.mehmettas.cent.ui.base

import android.app.AlertDialog
import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.mehmettas.cent.R
import com.mehmettas.cent.utils.DialogUtils
import com.mehmettas.cent.utils.customscreens.LoadingDialog


abstract class BaseActivity: AppCompatActivity(), IBaseNavigator {
    @get:LayoutRes
    protected abstract val layoutId: Int?

    protected abstract fun initNavigator()

    protected abstract fun initUI()

    protected abstract fun initListener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutId?.let { setContentView(it) }
        initNavigator()
        initUI()
        initListener()
    }

    private val dialog: AlertDialog by lazy {
        LoadingDialog.Builder().setContext(this)
            .setCancelable(false)
            .build()
    }

    override fun showLoading() {
        dialog.show()
    }

    override fun hideLoading() {
        dialog.hide()
    }

    override fun onError(errorMessage: String) {
        val model = DialogUtils.DialogModel(
            "",
            errorMessage,
            0,
            "Tamam",
            "",
            R.drawable.ic_problem,
            false
        )

        DialogUtils.showAlertDialog(this, model, object : DialogUtils.DialogAlertListener {
            override fun onPositiveClick() {

            }

            override fun onNegativeClick() {

            }

        })

    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outR = Rect()
                v.getGlobalVisibleRect(outR)
                val isKeyboardOpen = !outR.contains(ev.rawX.toInt(), ev.rawY.toInt())
                if (isKeyboardOpen) {
                    print("Entro al IF")
                    v.clearFocus()
                    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
                v.isCursorVisible = (!isKeyboardOpen)

            }
        }
        return super.dispatchTouchEvent(ev)
    }

}