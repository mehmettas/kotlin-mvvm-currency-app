package com.mehmettas.cent.ui.base

interface IBaseNavigator {

    fun showLoading()

    fun hideLoading()

    fun onError(errorMessage:String)

}