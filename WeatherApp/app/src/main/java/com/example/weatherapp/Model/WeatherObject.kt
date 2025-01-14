package com.example.weatherapp.Model

data class WeatherObject (
    val weather: List<weatherArrayObject>,
    val main: mainObject,
    val wind: windObject
    ) {}
data class weatherArrayObject(val main: String,
                        val description: String,
                       val icon:String)

data class mainObject(val temp: Double,
                      val feels_like: Double,
    val humidity:Int
    ){}

data class windObject(val speed: Double){}