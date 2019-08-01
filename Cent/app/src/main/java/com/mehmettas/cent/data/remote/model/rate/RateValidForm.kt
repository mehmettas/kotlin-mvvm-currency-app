package com.mehmettas.cent.data.remote.model.rate

import com.google.gson.annotations.SerializedName

class RateValidForm(
    @SerializedName("code")
    var code:String,
    @SerializedName("rate")
    var rate:String
)