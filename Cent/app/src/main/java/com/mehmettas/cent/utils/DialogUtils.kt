package com.mehmettas.cent.utils

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.mehmettas.cent.R

object DialogUtils {
    data class DialogModel(
        var title: String? = null,
        var message: String? = null,
        var uniqueId: Int = 0,
        var positive: String? = null,
        var negative: String? = null,
        @DrawableRes
        var icon: Int = 0,
        var isNegativeButton: Boolean = false
    )

    interface DialogAlertListener {
        fun onPositiveClick()
        fun onNegativeClick()
    }

    fun showBaseAlertDialog(
        context: Context, title: String, message: String, positive: String, negative: String,
        listener: DialogAlertListener
    ) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(positive) { dialog, id ->
                listener.onPositiveClick()
                dialog.cancel()
            }
            .setNegativeButton(negative) { dialog, id ->
                listener.onNegativeClick()
                dialog.cancel()
            }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    fun showAlertDialog(
        context: Context,
        dialogModel: DialogModel,
        listener: DialogAlertListener? = null
    ) {
        val dialog = AlertDialog.Builder(context)
        val layoutInflater = LayoutInflater.from(context)
        val dialogView = layoutInflater.inflate(R.layout.dialog_layout, null)
        val icon = dialogView.findViewById<AppCompatImageView>(R.id.imgIconDialog)
        val message = dialogView.findViewById<AppCompatTextView>(R.id.txtMessageDialog)
        val btnOk = dialogView.findViewById<AppCompatButton>(R.id.btnOkDialog)
        val btnPositive = dialogView.findViewById<AppCompatButton>(R.id.btnPositiveDialog)
        val btnNegative = dialogView.findViewById<AppCompatButton>(R.id.btnNegativeDialog)
        val lnNegative = dialogView.findViewById<LinearLayout>(R.id.lnNegative)
        dialog.setView(dialogView)

        icon.setImageDrawable(ContextCompat.getDrawable(context, dialogModel.icon))
        message.text = dialogModel.message


        if (dialogModel.isNegativeButton) {
            lnNegative.visibility = View.VISIBLE
            btnOk.visibility = View.GONE
            btnPositive.text = dialogModel.positive
            btnNegative.text = dialogModel.negative
        } else {
            lnNegative.visibility = View.GONE
            btnOk.visibility = View.VISIBLE
            btnOk.text = dialogModel.positive
        }

        val alertDialog = dialog.create()
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.show()
        val v = alertDialog.window.decorView
        v.setBackgroundResource(android.R.color.transparent)

        btnOk.setOnClickListener {
            listener?.onPositiveClick()
            alertDialog.cancel()
        }

        btnPositive.setOnClickListener {
            listener?.onPositiveClick()
            alertDialog.cancel()
        }

        btnNegative.setOnClickListener {
            listener?.onNegativeClick()
            alertDialog.cancel()
        }
    }

    fun showLoadingDialog(context: Context){

    }


}