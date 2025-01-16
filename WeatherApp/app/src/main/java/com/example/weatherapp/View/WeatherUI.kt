package com.example.weatherapp.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.weatherapp.Model.WeatherObject
import com.example.weatherapp.R

@Composable
fun WeatherUI(modifier: Modifier, cityName: String, wo : WeatherObject?){
    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        if (wo != null) {

            if (cityName.length < 15) {
                Text(cityName, fontSize = 40.sp, fontWeight = FontWeight.ExtraBold)
            }else {
                Text(cityName, fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)

            }
            AsyncImage(
                ImageRequest.Builder(LocalContext.current)
                    .data("https://openweathermap.org/img/wn/${ wo.weather.get(0).icon}@2x.png")
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.noimage),
                contentDescription = "weather icon",
                modifier = Modifier.fillMaxSize(0.4f).background(Color.Red))

            Spacer(Modifier.fillMaxHeight(0.2f))
            Text(wo.weather[0].description, fontSize = 30.sp)
            Spacer(Modifier.fillMaxHeight(0.2f))
            Text("Temp: " + wo.main.temp.toString(),  fontSize = 30.sp)
            Text("Feels Like: "  + wo.main.feels_like.toString(),  fontSize = 30.sp)
            Text("Wind Speed: "  + wo.wind.speed.toString(),  fontSize = 30.sp)

        }
    }


}