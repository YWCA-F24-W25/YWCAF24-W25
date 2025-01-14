package com.example.weatherapp.Model

import com.example.weatherapp.Networking.RetrofitClass


interface WeatherInterface {
    suspend fun getWeatherForOneCity(cityName: String) : WeatherObject
}

class WeatherAPIService {
    private val apiService = RetrofitClass.weatherAPI

    suspend fun getWeatherForOneCity(cityName: String) : WeatherObject{
      return  apiService.getWeatherForCity(cityName,
            "use your own",
            "metric")
    }
}