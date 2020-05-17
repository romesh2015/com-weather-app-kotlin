package com.weather.weathertestapp.utilities
import android.content.Context
import android.net.ConnectivityManager
import com.weather.weathertestapp.WeatherApp
import java.text.SimpleDateFormat
import java.util.*

object Utility {
    val isWifiAvailable: Boolean
        get() {
            val connMgr = WeatherApp.getsAppContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var isWifiConn = false
            for (network in connMgr.allNetworks) {
                val networkInfo = connMgr.getNetworkInfo(network)
                if (networkInfo.type == ConnectivityManager.TYPE_WIFI) {
                    isWifiConn = isWifiConn or networkInfo.isConnected
                }
            }
            return isWifiConn
        }

    val currentTime: String
        get() {
            val sdf = SimpleDateFormat("HH:mm:ss")
            return sdf.format(Date())
        }
}