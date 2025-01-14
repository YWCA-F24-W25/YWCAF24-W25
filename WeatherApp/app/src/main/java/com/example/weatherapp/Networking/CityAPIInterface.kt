package com.example.weatherapp.Networking

import retrofit2.http.GET
import retrofit2.http.Query

interface CityAPIInterface {
    // get all cities from api

    @GET("AutoCompleteCity")
    suspend fun getCitiesFromAPI(@Query("q")q: String): List<String>
    // coroutine == function runs in background - Async
}
