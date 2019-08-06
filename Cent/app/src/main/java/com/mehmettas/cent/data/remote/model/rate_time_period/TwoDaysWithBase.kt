package com.mehmettas.cent.data.remote.model.rate_time_period

import com.google.gson.annotations.SerializedName

class TwoDaysWithBase(
    @SerializedName("start_at")
    var startAt:String,
    @SerializedName("end_at")
    var endAt:String,
    @SerializedName("base")
    var base:String,
    @SerializedName("rates")
    var rates:String
)