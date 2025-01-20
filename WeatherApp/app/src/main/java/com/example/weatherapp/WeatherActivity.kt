package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.Model.WeatherObject
import com.example.weatherapp.Room.CityDatabase
import com.example.weatherapp.View.WeatherUI
import com.example.weatherapp.ViewModel.AppRepository
import com.example.weatherapp.ViewModel.CityViewModel
import com.example.weatherapp.ViewModel.ViewModelFactory
import com.example.weatherapp.ViewModel.WeatherViewModel
import com.example.weatherapp.ui.theme.WeatherAppTheme

class WeatherActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var selectedCity = intent.getStringExtra("city")

        var database = CityDatabase.getDBInstance(this)
        var cityDao = database.getCityDao()
        var appRepo = AppRepository(cityDao)
        var weatherViewModelFactory = ViewModelFactory(appRepo)
        var wvm = ViewModelProvider(this,weatherViewModelFactory)[WeatherViewModel::class.java]


        enableEdgeToEdge()
        setContent {



            WeatherAppTheme {
                if (selectedCity != null) {
                    wvm.getWeatherForCity(selectedCity)
                }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    if (selectedCity != null) {
                        WeatherUI(
                            modifier = Modifier.padding(innerPadding),
                            selectedCity,
                            wvm.apiWeatherObject
                        )
                    }
                    }

            }
        }
    }
}

