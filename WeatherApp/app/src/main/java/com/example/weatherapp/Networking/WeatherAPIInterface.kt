package com.example.weatherapp.Networking

import com.example.weatherapp.Model.WeatherObject
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPIInterface {
    @GET("weather?")
    suspend fun getWeatherForCity(@Query("q") q: String,
                                  @Query("appid") appid:String,
                                  @Query("units") units: String
                                  ): WeatherObject

}