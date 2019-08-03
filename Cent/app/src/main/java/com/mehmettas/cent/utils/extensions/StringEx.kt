package com.mehmettas.cent.utils.extensions

fun trimForBothSides(value:String?,start:Int,fromEnd:Int): String{
    return value?.substring(start,value.length.minus(fromEnd))!!
}
