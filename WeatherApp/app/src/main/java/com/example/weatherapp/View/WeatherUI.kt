package com.example.weatherapp.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter

import com.example.weatherapp.Model.WeatherObject

@Composable
fun WeatherUI(modifier: Modifier, cityName: String, wo : WeatherObject?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (wo != null) {

            if (cityName.length < 15) {
                Text(cityName, fontSize = 40.sp, fontWeight = FontWeight.ExtraBold)
            } else {
                Text(cityName, fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
            }
//            Image(
//                modifier = Modifier.fillMaxSize(0.4f),
//                painter = rememberAsyncImagePainter(
//                    "https://openweathermap.org/img/wn/${
//                        wo.weather.get(
//                            0
//                        ).icon
//                    }@4x.png"
//                ), contentDescription = "icon"
//            )
            var icon = wo.weather.get(0).icon
            AsyncImage(
                modifier = Modifier.fillMaxSize(0.4f),
                model = "https://openweathermap.org/img/wn/${icon}@4x.png",
                contentDescription = null,
            )
            Spacer(Modifier.fillMaxHeight(0.2f))
            Text(wo.weather[0].description, fontSize = 30.sp)
            Spacer(Modifier.fillMaxHeight(0.2f))
            Text("Temp: " + wo.main.temp.toString(), fontSize = 30.sp)
            Text("Feels Like: " + wo.main.feels_like.toString(), fontSize = 30.sp)
            Text("Wind Speed: " + wo.wind.speed.toString(), fontSize = 30.sp)

        }
    }

}