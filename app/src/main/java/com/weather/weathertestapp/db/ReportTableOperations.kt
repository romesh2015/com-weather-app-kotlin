package com.weather.weathertestapp.db
import android.os.AsyncTask
import com.weather.weathertestapp.WeatherApp
import com.weather.weathertestapp.db.DatabaseClient.Companion.getInstance
import com.weather.weathertestapp.utilities.AppConstants
import com.weather.weathertestapp.utilities.SharedPrefsHelper
import com.weather.weathertestapp.utilities.Tracer
import com.weather.weathertestapp.utilities.Utility
import com.weather.weathertestapp.weather.model.WeatherResponse
import org.greenrobot.eventbus.EventBus

class ReportTableOperations {
    fun saveWeatherReport(weatherResponse: WeatherResponse): Boolean {
        class SaveTask : AsyncTask<Void?, Void?, Boolean>() {
            override fun doInBackground(vararg params: Void?): Boolean  {
                val weatherDetail = WeatherDetail()
                weatherDetail.id = weatherResponse.id
                weatherDetail.location = weatherResponse.name
                weatherDetail.country = weatherResponse.sys?.country
                weatherDetail.weatherType = weatherResponse.weatherList?.get(0)?.main
                weatherDetail.temp = weatherResponse.main!!.temp
                weatherDetail.maxTemp = weatherResponse.main!!.tempMax
                weatherDetail.minTemp = weatherResponse.main!!.tempMin
                weatherDetail.feelLike = weatherResponse.main!!.feelsLike
                weatherDetail.pressure = weatherResponse.main!!.pressure
                weatherDetail.humidity = weatherResponse.main!!.humidity
                weatherDetail.visibility = weatherResponse.visibility
                weatherDetail.timeStamp = Utility.currentTime
                getInstance(WeatherApp.getsAppContext())!!.appDatabase
                        .reportDetailDAO()
                        ?.insertWeatherReport(weatherDetail)
                return true
            }

            override fun onPostExecute(aBoolean: Boolean) {
                super.onPostExecute(aBoolean)
                Tracer.info(TAG, "Record save status->$aBoolean")
                SharedPrefsHelper.getInstance()?.save(AppConstants.DEFAULT_FUNCTION_CALL_REQUEST, AppConstants.UPDATE_REQUEST_VAL)
                weatherData
            }


        }

        val st = SaveTask()
        st.execute()
        return false
    }

    val weatherData: Unit
        get() {
            class GetVideoDetail : AsyncTask<Void?, Void?, WeatherDetail?>() {
                var weatherDetail: WeatherDetail? = null
                protected override fun doInBackground(vararg params: Void?): WeatherDetail? {
                    weatherDetail = getInstance(WeatherApp.getsAppContext())!!.appDatabase
                            .reportDetailDAO()?.weatherReport
                    return weatherDetail
                }

                override fun onPostExecute(weatherDetail: WeatherDetail?) {
                    super.onPostExecute(weatherDetail)
                    EventBus.getDefault().post(weatherDetail)
                }

            }

            val getVideoDetail = GetVideoDetail()
            getVideoDetail.execute()
        }

    companion object {
        private val TAG = ReportTableOperations::class.java.simpleName
    }
}