package com.weather.weathertestapp.network

import com.weather.weathertestapp.weather.model.WeatherResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {
    /**
     * Weather update status Api Call
     */
    @get:GET(ApiEndPoints.WEATHER_STATUS + ApiEndPoints.API_KEY)
    @get:Headers("Content-Type: application/json")
    val weatherReport: Observable<WeatherResponse?>?
}