package com.weather.weathertestapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WeatherDetail")
class WeatherDetail {
    @JvmField
    @PrimaryKey(autoGenerate = false)
    var id: Long? = null

    @JvmField
    @ColumnInfo(name = "location")
    var location: String? = null

    @JvmField
    @ColumnInfo(name = "country")
    var country: String? = null

    @JvmField
    @ColumnInfo(name = "weather_type")
    var weatherType: String? = null

    @JvmField
    @ColumnInfo(name = "temp")
    var temp = 0f

    @JvmField
    @ColumnInfo(name = "max_temp")
    var maxTemp = 0f

    @JvmField
    @ColumnInfo(name = "min_temp")
    var minTemp = 0f

    @JvmField
    @ColumnInfo(name = "feel_like")
    var feelLike = 0f

    @JvmField
    @ColumnInfo(name = "pressure")
    var pressure = 0

    @JvmField
    @ColumnInfo(name = "humidity")
    var humidity = 0

    @JvmField
    @ColumnInfo(name = "visibility")
    var visibility: String? = null

    @JvmField
    @ColumnInfo(name = "time_stamp")
    var timeStamp: String? = null

}