package com.healthproviderapp.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

@SuppressWarnings("All")
class SharedPref {

    companion object {
        private val sharePref = SharedPref()
        private var sharedPreferences: SharedPreferences? = null
        private var editor: SharedPreferences.Editor? = null
        private val LOGINRESPONSE = "loginresponse"


        //The context passed into the getInstance should be application level context.
        fun getInstance(context: Context?): SharedPref {

            if (sharedPreferences == null) {
                if (context != null) {
                    sharedPreferences =
                        context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)
                    editor = sharedPreferences?.edit()
                }
            }

            return sharePref
        }
    }

    var loginResponse: String
        get() = sharedPreferences?.getString(LOGINRESPONSE, "")!!
        set(loginResponse) {
            editor!!.putString(LOGINRESPONSE, loginResponse)
            editor!!.commit()
        }

    fun clearAllData() {
        editor!!.clear().apply()
    }

    fun clearAll() {
        editor!!.clear()
        editor!!.commit()
    }

    fun save(key: String, value: Any?) {
        val editor = editor
        when {
            value is Boolean -> editor!!.putBoolean(key, (value as Boolean?)!!)
            value is Int -> editor!!.putInt(key, (value as Int?)!!)
            value is Float -> editor!!.putFloat(key, (value as Float?)!!)
            value is Long -> editor!!.putLong(key, (value as Long?)!!)
            value is String -> editor!!.putString(key, value as String?)
            value is Enum<*> -> editor!!.putString(key, value.toString())
            value != null -> throw RuntimeException("Attempting to save non-supported preference")
        }

        editor!!.commit()
    }

    fun delete(key: String) {
        if (sharedPreferences!!.contains(key)) {
            editor!!.remove(key).commit()
        }
    }

}