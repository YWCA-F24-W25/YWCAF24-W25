package com.example.weatherapp.Networking

import com.example.weatherapp.Model.CityAPIService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


// class RetrofitClass { }
// static var myObject = RetrofitClass()

object RetrofitClass {
    private val cityBaseURL = "http://gd.geobytes.com/"

    private val weatherBaseURL = "https://api.openweathermap.org/data/2.5/"


    var cityRettofitObject =
        Retrofit.Builder().
                addConverterFactory(GsonConverterFactory.create()).
                baseUrl(cityBaseURL).
                build()

    val cityApi = cityRettofitObject.create(CityAPIInterface::class.java)


    var weatherRetroitObject =  Retrofit.Builder().
                                 addConverterFactory(GsonConverterFactory.create()).
                                  baseUrl(weatherBaseURL).
                                build()
    val weatherAPI = weatherRetroitObject.create(WeatherAPIInterface::class.java)





}