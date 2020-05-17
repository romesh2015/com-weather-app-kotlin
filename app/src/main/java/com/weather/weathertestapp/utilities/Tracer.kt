package com.weather.weathertestapp.utilities

import android.util.Log

object Tracer {
    private const val LOG_ENABLE = true
    fun debug(TAG: String, message: String?) {
        if (LOG_ENABLE) {
            Log.d(Config.App_log + TAG, message)
        }
    }

    @JvmStatic
    fun info(TAG: String, message: String?) {
        if (LOG_ENABLE) {
            Log.i(Config.App_log + TAG, message)
        }
    }

    @JvmStatic
    fun warning(TAG: String, message: String?) {
        if (LOG_ENABLE) {
            Log.w(Config.App_log + TAG, message)
        }
    }
}