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


    @GET("weather?")
    suspend fun getWeatherForLocation(@Query("lat") lat: String,
                                      @Query("lon") lon: String,
                                  @Query("appid") appid:String,
                                  @Query("units") units: String
    ): WeatherObject
}