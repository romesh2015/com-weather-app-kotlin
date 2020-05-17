package com.weather.weathertestapp.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ApiErrorsResponseHandling {
    @SerializedName("cod")
    @Expose
    var errorCode = 0

    @SerializedName("message")
    @Expose
    var message: String? = null

}