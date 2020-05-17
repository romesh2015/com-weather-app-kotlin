package com.weather.weathertestapp.weather.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Main {
    @SerializedName("temp")
    @Expose
    var temp = 0f

    @SerializedName("feels_like")
    @Expose
    var feelsLike = 0f

    @SerializedName("temp_min")
    @Expose
    var tempMin = 0f

    @SerializedName("temp_max")
    @Expose
    var tempMax = 0f

    @SerializedName("pressure")
    @Expose
    var pressure = 0

    @SerializedName("humidity")
    @Expose
    var humidity = 0

}