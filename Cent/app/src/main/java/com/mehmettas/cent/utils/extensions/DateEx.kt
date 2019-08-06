package com.mehmettas.cent.utils.extensions

import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

fun getDateOfDaysAgo(daysAgo:Int): String
{
    val calendar = Calendar.getInstance()
    val format = SimpleDateFormat("yyyy-MM-dd")
    calendar.time = Date()
    calendar.add(Calendar.DATE,-daysAgo)
    val currentDate = format.format((calendar.time))
    return currentDate
}

fun getDaysOftheWeek(): ArrayList<String> {

    val days = arrayListOf<String>()
    val dateFormat = DateFormatSymbols()
    //val weekdays = dateFormat.getWeekdays()
    val weekdays = dateFormat.shortWeekdays
    for (weekday in weekdays) {
        days.add(weekday)
    }

    return days
}