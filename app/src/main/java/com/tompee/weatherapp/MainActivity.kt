package com.tompee.weatherapp

import android.Manifest
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tompee.model.LocationService
import com.tompee.model.WeatherApi
import com.tompee.model.WeatherLocationInfo
import kotlinx.android.synthetic.main.activity_main.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions


enum class MainWeather(val mainWeather : String) {
    THUNDER("Thunderstorm"),
    RAIN("Rain"),
    SNOW("Snow"),
    CLOUDY("Clouds"),
    CLEAR("Clear"),
    ATMOSPHERE("Atmosphere")
}

class MainActivity : AppCompatActivity() , EasyPermissions.PermissionCallbacks {
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        finish()
    }

    companion object {
        const val RC_LOCATION = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Request Permission
        locationTask()
    }

    private fun hasLocationPermission(): Boolean {
        return EasyPermissions.hasPermissions(this, Manifest.permission.ACCESS_FINE_LOCATION)
    }

    @AfterPermissionGranted(RC_LOCATION)
    fun locationTask() {
        if (hasLocationPermission()) {
            var newLocationService = LocationService(this).getCurrentLocation()
            newLocationService?.addOnSuccessListener {
                // Get Weather Information
                var newWeatherApi = WeatherApi()
                if(it != null) {
                    GetWeatherTask(this, newWeatherApi).execute(it.latitude, it.longitude)
                }
            }
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(
                    this,
                    getString(R.string.rationale_location),
                    RC_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    inner class GetWeatherTask(private val context : Context, private val weatherApi : WeatherApi) : AsyncTask<Double, Void, WeatherLocationInfo>() {

        override fun doInBackground(vararg input : Double?): WeatherLocationInfo? {
            return weatherApi.getWeatherInfoByCoord(input[0]!!, input[1]!!, context.getString(R.string.celsius), context.getString(R.string.apiKey))
        }

        override fun onPostExecute(locInfo : WeatherLocationInfo?) {
            val weatherMainDesc = locInfo?.listData!![0].weather[0].main
            val weatherDetailDesc = locInfo?.listData!![0].weather[0].description
            if(weatherMainDesc == MainWeather.ATMOSPHERE.mainWeather){
                textDesc.text = weatherDetailDesc
            } else {
                textDesc.text = weatherMainDesc
            }
            setWeatherIcon(weatherMainDesc, weatherDetailDesc)

            textLocation.text = locInfo?.city?.name
            textTemp.text = locInfo?.listData!![0].main?.temp.toString() + context.getString(R.string.celsiusChar)
            textMinTemp.text = locInfo?.listData!![0].main?.tempMin.toString() + context.getString(R.string.celsiusChar)
            textMaxTemp.text = locInfo?.listData!![0].main?.tempMax.toString() + context.getString(R.string.celsiusChar)
            textHumidityVal.text = locInfo?.listData!![0].main?.humidity.toString() + context.getString(R.string.humidUnit)
            textRainVal.text = locInfo?.listData!![0].rain!!.rain_3h.toString() + context.getString(R.string.rainUnit)
        }

        private fun setWeatherIcon(mainDesc : String, detail : String) {

            when(mainDesc){
                MainWeather.RAIN.mainWeather -> icon.setImageResource(R.drawable.rain)
                MainWeather.SNOW.mainWeather -> icon.setImageResource(R.drawable.snow)
                MainWeather.THUNDER.mainWeather -> icon.setImageResource(R.drawable.tstorms)
                MainWeather.CLEAR.mainWeather -> icon.setImageResource(R.drawable.clear)
                MainWeather.CLOUDY.mainWeather -> icon.setImageResource(R.drawable.cloudy)
                MainWeather.ATMOSPHERE.mainWeather -> {
                    if(detail.contentEquals("fog")){
                        icon.setImageResource(R.drawable.fog)
                    } else {
                        icon.setImageResource(R.drawable.unknown)
                    }
                }
                else -> icon.setImageResource(R.drawable.unknown)

            }



        }
    }
}
