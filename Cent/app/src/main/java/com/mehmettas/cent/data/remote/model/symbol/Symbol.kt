package com.mehmettas.cent.data.remote.model.symbol

import com.google.gson.annotations.SerializedName

class Symbol(
    @SerializedName("status")
    var status:String,
    @SerializedName("currencies")
    var currencies:ArrayList<Currency>
)