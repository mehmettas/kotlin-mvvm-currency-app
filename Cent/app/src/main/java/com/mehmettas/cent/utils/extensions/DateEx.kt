package com.mehmettas.cent.utils.extensions

import java.util.*

fun getDateOfDaysAgo(daysAgo:Int): String
{
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR,-daysAgo)
    return calendar.time.toString()
}