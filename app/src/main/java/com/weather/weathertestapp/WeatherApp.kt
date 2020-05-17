package com.weather.weathertestapp
import android.app.Application
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import com.weather.weathertestapp.jobService.WeatherReportJobService
import com.weather.weathertestapp.utilities.AppConstants
import com.weather.weathertestapp.utilities.SharedPrefsHelper
import com.weather.weathertestapp.utilities.Tracer.info

class WeatherApp : Application() {
    override fun onCreate() {
        super.onCreate()
        wContext = this
        if (!SharedPrefsHelper.getInstance()!!.has(AppConstants.IS_JOB_EXECUTED)) {
            SharedPrefsHelper.getInstance()!!.save(AppConstants.IS_JOB_EXECUTED, AppConstants.JOB_DEFAULT)
        }
        startJob()

    }

    private fun startJob() {
        if (AppConstants.JOB_DEFAULT.equals(SharedPrefsHelper.getInstance()!!.get<String>(AppConstants.IS_JOB_EXECUTED), ignoreCase = true)) {
            // This job will run automatically with in the interval of 2 hours.
            val componentName = ComponentName(packageName, WeatherReportJobService::class.java.name)
            val info = JobInfo.Builder(123, componentName)
                    .setPersisted(true)
                    .setPeriodic(15 * 60 * 1000L)
                    .build()
            val scheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            val resultCode = scheduler.schedule(info)
            if (resultCode == JobScheduler.RESULT_SUCCESS) {
                info(TAG, "Job scheduled")
            } else {
                info(TAG, "Job scheduling failed")
            }
        }
    }

    companion object {
        val TAG = WeatherApp::class.java.simpleName
        private var wContext: Context? = null

        fun getsAppContext(): Context {
            return wContext!!.applicationContext
        }


    }
}