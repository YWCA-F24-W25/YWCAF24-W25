package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.Model.WeatherObject
import com.example.weatherapp.View.WeatherUI
import com.example.weatherapp.ViewModel.CityViewModel
import com.example.weatherapp.ViewModel.WeatherViewModel
import com.example.weatherapp.ui.theme.WeatherAppTheme

class WeatherActivity : ComponentActivity() {

    lateinit var wvm : Lazy<WeatherViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var selectedCity = intent.getStringExtra("city")

        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                wvm = viewModels<WeatherViewModel>()
                if (selectedCity != null) {
                    wvm.value.getWeatherForCity(selectedCity)
                }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    if (selectedCity != null) {
                        WeatherUI(
                            modifier = Modifier.padding(innerPadding),
                            selectedCity,
                            wvm.value.apiWeatherObject
                        )
                    }
                    }

            }
        }
    }
}

