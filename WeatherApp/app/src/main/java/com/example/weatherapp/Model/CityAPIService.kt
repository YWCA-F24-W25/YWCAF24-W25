package com.example.weatherapp.Model

import com.example.weatherapp.Networking.RetrofitClass

interface CityAPIServiceInterface {
    suspend fun getCitiesFromAPI(text: String) : List<String>
}

class CityAPIService {
        // connecting API to fetch cities data

        private val apiService = RetrofitClass.cityApi

        suspend fun getCitiesFromAPI(text: String) : List<String>{
           return apiService.getCitiesFromAPI(text)
        }
}