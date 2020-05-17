package com.weather.weathertestapp.weather.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Coordinate {
    @SerializedName("lon")
    @Expose
    var longitude = 0f

    @SerializedName("lat")
    @Expose
    var latitude = 0f

}