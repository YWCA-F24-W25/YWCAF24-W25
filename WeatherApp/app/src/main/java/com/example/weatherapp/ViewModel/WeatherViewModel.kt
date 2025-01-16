package com.example.weatherapp.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.Model.WeatherObject
import kotlinx.coroutines.launch

class WeatherViewModel(var appRepo : AppRepository ) : ViewModel() {
    var apiWeatherObject by mutableStateOf<WeatherObject?>(null)

    // If I can create App Repo in VM.
    // var appRepo : AppRepository = AppRepository()



    fun getWeatherForCity(name:String){
        viewModelScope.launch {
            val wo = appRepo.getWeatherForOneCity(name)
            apiWeatherObject = wo
        }
    }



}