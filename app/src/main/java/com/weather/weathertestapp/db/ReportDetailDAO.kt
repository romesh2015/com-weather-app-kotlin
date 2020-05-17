package com.weather.weathertestapp.db

import androidx.room.*

@Dao
interface ReportDetailDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherReport(vararg weatherDetails: WeatherDetail?)

    @get:Query("SELECT * FROM WeatherDetail")
    val weatherReport: WeatherDetail?

    @Update
    fun updateUsers(vararg weatherDetails: WeatherDetail?)
}