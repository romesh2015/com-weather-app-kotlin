package com.weather.weathertestapp.db

import android.content.Context
import androidx.room.Room

class DatabaseClient(private val mCtx: Context?) {

    //our app database object
    val appDatabase: WeatherDB

    companion object {
        private var mInstance: DatabaseClient? = null

        @JvmStatic
        @Synchronized
        fun getInstance(mCtx: Context?): DatabaseClient? {
            if (mInstance == null) {
                mInstance = DatabaseClient(mCtx)
            }
            return mInstance
        }
    }

    init {
        appDatabase = Room.databaseBuilder(mCtx!!, WeatherDB::class.java, "DBWeatherApp").build()
    }
}