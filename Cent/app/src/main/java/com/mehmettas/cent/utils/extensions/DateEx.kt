package com.mehmettas.cent.utils.extensions

import java.text.SimpleDateFormat
import java.util.*

fun getDateOfDaysAgo(daysAgo:Int): String
{
    val calendar = Calendar.getInstance()
    val format = SimpleDateFormat("yyyy-MM-dd")
    calendar.time = Date()
    calendar.add(Calendar.DATE,-daysAgo)
    val currentDate = format.format((calendar.time))
    return currentDate
}