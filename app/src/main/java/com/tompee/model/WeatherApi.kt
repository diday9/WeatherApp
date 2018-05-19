package com.tompee.model

import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class WeatherApi{

    val service : WeatherApiService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
        service = retrofit.create(WeatherApiService::class.java)
    }

    fun getWeatherInfoByCoord(lat : Double, lon : Double, units : String, appId : String) : WeatherLocationInfo? {
        val apiResponse = service.getForecast(lat, lon, units, appId).execute()
        if(apiResponse.isSuccessful){
            return apiResponse.body()
        }
        return null
    }
}