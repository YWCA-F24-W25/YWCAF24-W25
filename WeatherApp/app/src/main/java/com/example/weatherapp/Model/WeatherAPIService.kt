package com.example.weatherapp.Model

import com.example.weatherapp.Networking.RetrofitClass


interface WeatherInterface {
    suspend fun getWeatherForOneCity(cityName: String) : WeatherObject
}

class WeatherAPIService {
    private val apiService = RetrofitClass.weatherAPI

    suspend fun getWeatherForOneCity(cityName: String) : WeatherObject{
      return  apiService.getWeatherForCity(cityName,
            "071c3ffca10be01d334505630d2c1a9c",
            "metric")
    }

    suspend fun getWeatherForLocation(lat: String, lon : String) : WeatherObject{
        return  apiService.getWeatherForLocation(lat, lon,
            "071c3ffca10be01d334505630d2c1a9c",
            "metric")
    }


}