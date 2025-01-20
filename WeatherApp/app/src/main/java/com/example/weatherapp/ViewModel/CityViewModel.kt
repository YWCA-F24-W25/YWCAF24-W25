package com.example.weatherapp.ViewModel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.Room.City
import com.example.weatherapp.Room.CityDatabase
import kotlinx.coroutines.launch

class CityViewModel(var appRepo : AppRepository) : ViewModel() {

    var apiListOfCities by mutableStateOf<List<String>>(emptyList())
    var databaseListOfFavCities by mutableStateOf<List<City>>(emptyList())

    // AppRepository needs no parameters
   // If I can create App Repo in VM.
    // var appRepo : AppRepository = AppRepository()

    fun setdbList(){
        databaseListOfFavCities = emptyList()
    }

    fun setList(){
        apiListOfCities = emptyList()
    }

    fun getAllCitiesFromAPI(query: String){
        viewModelScope.launch {
        // background thread // async
            var apilist = appRepo.cityApiService.getCitiesFromAPI(query)
            apiListOfCities = apilist
        }
    }

    // database Functions

    fun insertNewCityInDB(c: City) {
        viewModelScope.launch {
            appRepo.insertNewCityInDB(c)
            databaseListOfFavCities = databaseListOfFavCities + c

        }
    }

    fun updateOneCity(c: City){
        viewModelScope.launch {
            appRepo.updateOneCity(c)
            databaseListOfFavCities = getAllCities()
    }
    }

    fun deleteCity(c: City) {
        viewModelScope.launch {
            appRepo.deleteCity(c)
            databaseListOfFavCities = getAllCities()
        }
    }

    fun searchForCity(t: String):List<City> {
        viewModelScope.launch {
           var results =  appRepo.searchForCity(t)
            databaseListOfFavCities = results
        }
        return databaseListOfFavCities
    }

    fun getAllCities():List<City> {
        viewModelScope.launch {
          var result =  appRepo.getAllCities()
            databaseListOfFavCities = result
        }
      return  databaseListOfFavCities
    }





}