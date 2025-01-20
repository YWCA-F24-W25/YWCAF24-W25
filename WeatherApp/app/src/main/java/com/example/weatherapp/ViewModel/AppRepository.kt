package com.example.weatherapp.ViewModel

import com.example.weatherapp.Model.CityAPIService
import com.example.weatherapp.Model.CityAPIServiceInterface
import com.example.weatherapp.Model.WeatherAPIService
import com.example.weatherapp.Model.WeatherInterface
import com.example.weatherapp.Model.WeatherObject
import com.example.weatherapp.Room.City
import com.example.weatherapp.Room.CityDAO

class AppRepository (private val cityDao: CityDAO) : CityAPIServiceInterface, WeatherInterface {
    // source of truth
    var cityApiService =  CityAPIService()
    var weatherAPIService = WeatherAPIService()

    override suspend fun getCitiesFromAPI(text: String): List<String> {
       return cityApiService.getCitiesFromAPI(text)
    }

    override suspend fun getWeatherForOneCity(cityName: String): WeatherObject {
       return weatherAPIService.getWeatherForOneCity(cityName)
    }

    suspend fun getWeatherForLocation(lat: String, lon: String): WeatherObject {
        return weatherAPIService.getWeatherForLocation(lat,lon)
    }

    suspend fun insertNewCityInDB(c: City){
        cityDao.addNewCityToDB(c)
    }

    suspend fun updateOneCity(c: City){
        cityDao.updateOneCity(c)
    }

    suspend fun deleteCity(c: City){
        cityDao.deleteOneCityFromDB(c)
    }

    suspend fun searchForCity(t: String):List<City>{
      return cityDao.getCitiesEqualsTo(t)
    }

    suspend fun getAllCities():List<City>{
        return cityDao.getAllCitiesFromDB()
    }

}