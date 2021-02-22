package com.healthproviderapp.utils

import android.content.Context
import android.content.res.Resources
import android.widget.Toast


fun Context.toast(message: CharSequence): Toast = Toast
    .makeText(this, message, Toast.LENGTH_SHORT)
    .apply {
        show()
    }

fun Context.toast(message: Int): Toast = toast(resources.getString(message))

fun getPixelFromDp(dp: Float): Float {
    return dp * Resources.getSystem().displayMetrics.density
}

fun getDPFromPixel(pixel: Float): Float {
    return pixel / Resources.getSystem().displayMetrics.density
}

fun isStringCheckWithReturn(stText: String?, returnValue: String): String {
    return if (!(stText == null || stText.equals("", ignoreCase = true) || stText.equals(
            "null",
            ignoreCase = true
        ))
    ) {
        stText
    } else returnValue
}

fun isStringCheck(stText: String?): Boolean {
    return !(stText == null || stText.equals("", ignoreCase = true) || stText.equals(
        "null",
        ignoreCase = true
    ))
}



