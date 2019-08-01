package com.mehmettas.cent.data.remote.model.base

import com.google.gson.annotations.SerializedName
import com.mehmettas.cent.data.remote.model.rate.Rates

open class BaseResponse<out T>(
    @SerializedName("rates")
    val data: T? = null,
    @SerializedName("base")
    var base:String = "",
    @SerializedName("date")
    var date:String = ""
)