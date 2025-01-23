package com.example.weatherapp.ViewModel

import com.example.weatherapp.Model.CityAPIService
import com.example.weatherapp.Model.CityAPIServiceInterface
import com.example.weatherapp.Model.WeatherAPIService
import com.example.weatherapp.Model.WeatherInterface
import com.example.weatherapp.Model.WeatherObject
import com.example.weatherapp.Room.City
import com.example.weatherapp.Room.CityDAO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppRepository (private val cityDao: CityDAO) :
    CityAPIServiceInterface,
    WeatherInterface {
    // Live Update from Room DB - Observable - Flow


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

    suspend fun insertNewCityInDB(c: City):Boolean{
        var isAlreadyInDB = false
        var list = getSimilarCities(c.name)// check for same city in db
        if (list.size == 0){
            cityDao.addNewCityToDB(c)
        }else {
            isAlreadyInDB = true
        }
        return isAlreadyInDB
    }

    suspend fun updateOneCity(c: City){
        cityDao.updateOneCity(c)
    }
    suspend fun deleteCity(c: City){
        cityDao.deleteOneCityFromDB(c)
    }
    fun searchForCity(t: String):Flow<List<City>>{
      return  cityDao.getAllCitiesFromDB().map { lists ->
          lists.filter {
              city -> city.name.equals(t)
          }
      }
    }
    suspend fun getSimilarCities(t: String):List<City>{
        return cityDao.getCitiesEqualsTo(t)
    }

    fun getAllCities(): Flow<List<City>> {
        return cityDao.getAllCitiesFromDB()
    }

}