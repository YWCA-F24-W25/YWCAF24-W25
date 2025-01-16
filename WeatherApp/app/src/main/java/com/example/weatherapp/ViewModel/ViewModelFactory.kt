package com.example.weatherapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras

class ViewModelFactory(private val appRepo : AppRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(CityViewModel::class.java)){
            return CityViewModel(appRepo) as T
        }else if (modelClass.isAssignableFrom(WeatherViewModel::class.java)){
            return WeatherViewModel(appRepo) as T
        }
        else {
            throw IllegalArgumentException("Not matching class")
        }
    }
}
