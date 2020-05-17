package com.weather.weathertestapp.jobService
import android.app.job.JobParameters
import android.app.job.JobService
import android.widget.Toast
import com.weather.weathertestapp.R
import com.weather.weathertestapp.utilities.AppConstants
import com.weather.weathertestapp.utilities.SharedPrefsHelper
import com.weather.weathertestapp.utilities.Tracer
import com.weather.weathertestapp.utilities.Utility
import com.weather.weathertestapp.weather.presenter.WeatherPresenter
class WeatherReportJobService : JobService() {
    private var jobCancelled = false
    private var weatherPresenter: WeatherPresenter? = null
    override fun onStartJob(params: JobParameters): Boolean {
        Tracer.info(TAG, "Job started")
        weatherPresenter = WeatherPresenter(this)
        if (Utility.isWifiAvailable) {
            SharedPrefsHelper.getInstance()?.save(AppConstants.DEFAULT_FUNCTION_CALL_REQUEST, AppConstants.UPDATE_REQUEST_VAL)
            weatherPresenter!!.weatherInfo
        } else {
            Toast.makeText(this, getString(R.string.connection), Toast.LENGTH_LONG).show()
        }
        jobFinished(params, false)
        Tracer.info(TAG, "Job finished")
        return true
    }

    override fun onStopJob(params: JobParameters): Boolean {
        jobCancelled = true
        return true
    }

    companion object {
        private val TAG = WeatherReportJobService::class.java.simpleName
    }
}