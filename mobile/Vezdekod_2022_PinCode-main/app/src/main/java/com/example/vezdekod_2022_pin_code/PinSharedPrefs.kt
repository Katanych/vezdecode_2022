package com.example.vezdekod_2022_pin_code

import android.content.Context

class PinSharedPrefs(context: Context) : PinInterface {

    private val key = "pin"
    private val prefs = context.getSharedPreferences(key, Context.MODE_PRIVATE)

    override fun setPin(pin: String) {
        prefs.edit().apply {
            putString(key, pin)
            apply()
        }
    }

    override fun delete() {
        prefs.edit().apply {
            putString(key, "")
            apply()
        }
    }

    override fun getPin(callback: (String) -> Unit) {
        prefs.getString(key, "")?.let { callback.invoke(it) }
    }
}