package com.weather.weathertestapp.weather.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Wind {
    @SerializedName("speed")
    @Expose
    var speed = 0f

    @SerializedName("deg")
    @Expose
    var deg = 0

}