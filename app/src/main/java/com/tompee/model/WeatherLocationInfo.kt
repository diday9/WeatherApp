package com.tompee.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class WeatherLocationInfo(
        @JsonProperty("cod")
        @get:JsonProperty("cod")
        @set:JsonProperty("cod")
        var cod: String = "",

        @JsonProperty("message")
        @get:JsonProperty("message")
        @set:JsonProperty("message")
        var message: Double = 0.0,

        @JsonProperty("cnt")
        @get:JsonProperty("cnt")
        @set:JsonProperty("cnt")
        var cnt: Int = 0,

        @JsonProperty("list")
        @get:JsonProperty("list")
        @set:JsonProperty("list")
        var listData: List<ListData> = listOf(),

        @JsonProperty("city")
        @get:JsonProperty("city")
        @set:JsonProperty("city")
        var city: City? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class City(
        @JsonProperty("id")
        @get:JsonProperty("id")
        @set:JsonProperty("id")
        var id: Int = 0,

        @JsonProperty("name")
        @get:JsonProperty("name")
        @set:JsonProperty("name")
        var name: String = "",

        @JsonProperty("country")
        @get:JsonProperty("country")
        @set:JsonProperty("country")
        var country: String = ""
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class ListData(
        @JsonProperty("dt")
        @get:JsonProperty("dt")
        @set:JsonProperty("dt")
        var dt: Int = 0,

        @JsonProperty("main")
        @get:JsonProperty("main")
        @set:JsonProperty("main")
        var main: Main? = null,

        @JsonProperty("weather")
        @get:JsonProperty("weather")
        @set:JsonProperty("weather")
        var weather: List<Weather> = listOf(),

        @JsonProperty("rain")
        @get:JsonProperty("rain")
        @set:JsonProperty("rain")
        var rain: Rain? = null,

        @JsonProperty("dt_txt")
        @get:JsonProperty("dt_txt")
        @set:JsonProperty("dt_txt")
        var dtTxt: String = ""
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Main(
        @JsonProperty("temp")
        @get:JsonProperty("temp")
        @set:JsonProperty("temp")
        var temp: Double = 0.0,

        @JsonProperty("temp_min")
        @get:JsonProperty("temp_min")
        @set:JsonProperty("temp_min")
        var tempMin: Double = 0.0,

        @JsonProperty("temp_max")
        @get:JsonProperty("temp_max")
        @set:JsonProperty("temp_max")
        var tempMax: Double? = 0.0,

        @JsonProperty("humidity")
        @get:JsonProperty("humidity")
        @set:JsonProperty("humidity")
        var humidity: Int = 0
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Rain(

        @JsonProperty("3h")
        @get:JsonProperty("3h")
        @set:JsonProperty("3h")
        var rain_3h: Double = 0.0
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Weather(
        @JsonProperty("main")
        @get:JsonProperty("main")
        @set:JsonProperty("main")
        var main: String = "",

        @JsonProperty("description")
        @get:JsonProperty("description")
        @set:JsonProperty("description")
        var description: String = ""
)