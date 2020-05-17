package com.weather.weathertestapp.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WeatherDetail::class], version = 1, exportSchema = false)
abstract class WeatherDB : RoomDatabase() {
    abstract fun reportDetailDAO(): ReportDetailDAO?
}