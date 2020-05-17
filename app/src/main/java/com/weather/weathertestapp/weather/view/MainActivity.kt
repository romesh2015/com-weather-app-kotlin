package com.weather.weathertestapp.weather.view

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.weather.weathertestapp.R
import com.weather.weathertestapp.db.ReportTableOperations
import com.weather.weathertestapp.db.WeatherDetail
import com.weather.weathertestapp.network.ApiErrorsResponseHandling
import com.weather.weathertestapp.utilities.AppConstants
import com.weather.weathertestapp.utilities.SharedPrefsHelper.Companion.getInstance
import com.weather.weathertestapp.utilities.Tracer.warning
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MainActivity : AppCompatActivity() {
    private var tvLocation: TextView? = null
    private var tvTemperature: TextView? = null
    private var tvTempMaxMIn: TextView? = null
    private var tvFeelLIke: TextView? = null
    private var tvPressure: TextView? = null
    private var tvHumidity: TextView? = null
    private var tvVisibility: TextView? = null
    private var tvUpdateTime: TextView? = null
    private var imageViewIcon: ImageView? = null
    private var reportTableOperations: ReportTableOperations? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        reportTableOperations = ReportTableOperations()
        tvLocation = findViewById(R.id.textView)
        tvTemperature = findViewById(R.id.textView1)
        tvTempMaxMIn = findViewById(R.id.textView2)
        tvFeelLIke = findViewById(R.id.textView3)
        tvPressure = findViewById(R.id.textView4)
        tvHumidity = findViewById(R.id.textView5)
        tvVisibility = findViewById(R.id.textView6)
        tvUpdateTime = findViewById(R.id.textView7)
        imageViewIcon = findViewById(R.id.imageView)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
        if (AppConstants.UPDATE_REQUEST_VAL.equals(getInstance()!!.get<String>(AppConstants.DEFAULT_FUNCTION_CALL_REQUEST), ignoreCase = true)) {
            reportTableOperations!!.weatherData
        }
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun onWeatherReportSuccess(weatherResponse: WeatherDetail) {
        imageViewIcon!!.visibility = View.VISIBLE
        tvLocation!!.text = """
            ${getString(R.string.address)}: ${weatherResponse.location}, ${weatherResponse.country}

            ${getString(R.string.weather)}: ${weatherResponse.weatherType}
            """.trimIndent()
        tvTemperature!!.text=weatherResponse.temp.toString()+ getString(R.string.temp)
        tvTempMaxMIn!!.text=weatherResponse.maxTemp.toString()+ " / " + weatherResponse.minTemp + getString(R.string.temp)
        tvFeelLIke!!.text = getString(R.string.feel_like) + ": " + weatherResponse.feelLike
        tvPressure!!.text = getString(R.string.pressure) + ": " + weatherResponse.pressure
        tvHumidity!!.text = getString(R.string.humidity) + ": " + weatherResponse.humidity
        tvVisibility!!.text = getString(R.string.visibility) + ": " + weatherResponse.visibility
        tvUpdateTime!!.text = getString(R.string.timestamp) + ": " + weatherResponse.timeStamp
        getInstance()!!.save(AppConstants.IS_JOB_EXECUTED, "1")
    }

    @Subscribe
    fun onWeatherReportFailed(apiErrorsResponseHandling: ApiErrorsResponseHandling) {
        imageViewIcon!!.visibility = View.GONE
        warning(TAG, apiErrorsResponseHandling.message)
        Toast.makeText(this, apiErrorsResponseHandling.message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }
}