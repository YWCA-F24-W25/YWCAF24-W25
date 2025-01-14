package com.example.weatherapp.ViewModel

import com.example.weatherapp.Model.CityAPIService
import com.example.weatherapp.Model.CityAPIServiceInterface
import com.example.weatherapp.Model.WeatherAPIService
import com.example.weatherapp.Model.WeatherInterface
import com.example.weatherapp.Model.WeatherObject

class AppRepository : CityAPIServiceInterface, WeatherInterface {
// source of truth
    var cityApiService =  CityAPIService()
    var weatherAPIService = WeatherAPIService()

    override suspend fun getCitiesFromAPI(text: String): List<String> {
       return cityApiService.getCitiesFromAPI(text)
    }

    override suspend fun getWeatherForOneCity(cityName: String): WeatherObject {
       return weatherAPIService.getWeatherForOneCity(cityName)
    }

}