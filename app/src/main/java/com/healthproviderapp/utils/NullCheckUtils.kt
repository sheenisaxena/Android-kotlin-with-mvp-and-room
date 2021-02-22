package com.healthproviderapp.utils


fun getStringFromParam(value : String, blank : String): String{
    if (value.isNullOrEmpty() || value == "null") {
        return blank
    }
    return value
}

fun getIntValueFromParam(value : String): Int{
    if (value.isNullOrBlank() || value == "null") {
        return 0
    }
    return value.toInt()
}

fun getDoubleValueFromParam(value : String, ret:Double): Double{
    if (value.isNullOrBlank() || value == "null") {
        return ret
    }
    return value.toDouble()
}