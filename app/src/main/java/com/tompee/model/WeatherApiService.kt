package com.tompee.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    // By Geographic Coordinates
    @GET("data/2.5/forecast")
    fun getForecast(@Query("lat") lat : Double, @Query("lon") lon : Double,
                    @Query("units") units : String, @Query("appid") appId : String) : Call<WeatherLocationInfo>

    // By ZIP Code
    @GET("data/2.5/forecast")
    fun getForecast(@Query("id") cityId : Int, @Query("units") units : String,
                    @Query("appid") appId : String) : Call<WeatherLocationInfo>
}