package com.example.weatherapp.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.Model.City
import kotlinx.coroutines.launch

class CityViewModel(var appRepo : AppRepository ) : ViewModel() {

    var apiListOfCities by mutableStateOf<List<String>>(emptyList())

    // AppRepository needs no parameters
   // If I can create App Repo in VM.
    // var appRepo : AppRepository = AppRepository()



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

}