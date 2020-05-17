package com.weather.weathertestapp.weather.presenter

import android.content.Context
import com.google.gson.Gson
import com.weather.weathertestapp.db.ReportTableOperations
import com.weather.weathertestapp.network.ApiClient
import com.weather.weathertestapp.network.ApiErrorsResponseHandling
import com.weather.weathertestapp.utilities.Tracer.warning
import com.weather.weathertestapp.weather.model.WeatherResponse
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus
import retrofit2.HttpException

class WeatherPresenter(private val mContext: Context) {
    private val TAG = WeatherPresenter::class.java.simpleName
    val weatherInfo: Unit
        get() {
            val observable =ApiClient.create().weatherReport
            observable!!.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).map { UpdateAppLanguage: WeatherResponse? -> UpdateAppLanguage }.subscribe(object : Observer<WeatherResponse?> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(t: WeatherResponse) {
                    val reportTableOperations = ReportTableOperations()
                    reportTableOperations.saveWeatherReport(t!!)
                }

                override fun onError(e: Throwable) {
                    var errorResponse: ApiErrorsResponseHandling? = null
                    try {
                        val code = (e as HttpException).response().errorBody()!!.string()
                        warning(TAG, code)
                        val gson = Gson()
                        errorResponse = gson.fromJson(code, ApiErrorsResponseHandling::class.java)
                        EventBus.getDefault().unregister(this)
                        EventBus.getDefault().post(errorResponse)
                    } catch (ex: Exception) {
                        warning(TAG, ex.message)
                        errorResponse = ApiErrorsResponseHandling()
                        errorResponse.message = "Something went wrong, please try later!"
                        EventBus.getDefault().unregister(this)
                        EventBus.getDefault().post(errorResponse)
                    }
                }

                override fun onComplete() {}
            })
        }

}