package com.example.weatherapp.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.weatherapp.Model.WeatherObject

@Composable
fun WeatherUI(modifier: Modifier, wo : WeatherObject?){
    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        if (wo != null) {
            Text(wo.main.temp.toString())
            Text(wo.main.feels_like.toString())
            Text(wo.weather[0].description)
        }
    }


}