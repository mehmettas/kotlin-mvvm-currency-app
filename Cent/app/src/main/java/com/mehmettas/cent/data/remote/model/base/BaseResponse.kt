package com.mehmettas.cent.data.remote.model.base

import com.google.gson.annotations.SerializedName
import com.mehmettas.cent.data.remote.model.rate.Rates

class BaseResponse(
    @SerializedName("rates")
    var rates:Rates,
    @SerializedName("base")
    var base:String,
    @SerializedName("date")
    var date:String
)