package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.Room.City
import com.example.weatherapp.Room.CityDatabase
import com.example.weatherapp.View.CityTable
import com.example.weatherapp.View.SearchBarUI
import com.example.weatherapp.ViewModel.AppRepository
import com.example.weatherapp.ViewModel.CityViewModel
import com.example.weatherapp.ViewModel.ViewModelFactory
import com.example.weatherapp.ui.theme.WeatherAppTheme

class FavoriteCitiesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        var database = CityDatabase.getDBInstance(this)
        var cityDao = database.getCityDao()
        var appRepo = AppRepository(cityDao)
        var cityViewModelFactory = ViewModelFactory(appRepo)
        var vm = ViewModelProvider(this,cityViewModelFactory)[CityViewModel::class.java]

        setContent {
            WeatherAppTheme {
                    Scaffold(
                    modifier = Modifier.fillMaxSize()
                )
                { innerPadding ->
                    var dblist: List<City>
                    var dbStringList = remember { mutableStateListOf<String>() }
                    var isInSearch = remember { false }
                    var searchQuery = remember { "" }
                    if (!isInSearch) {
                        dblist = vm.getAllCities()

                        for (city in dblist) {
                            dbStringList += city.name
                        }
                    } else {
                        dblist = vm.searchForCity(searchQuery)
                        dbStringList.removeAll(dbStringList)
                        for (city in dblist) {
                            dbStringList += city.name
                        }
                    }
     Column(modifier = Modifier.padding(innerPadding)) {
                            Row(
                                modifier = Modifier.fillMaxHeight(0.15f)
                            ) {
                                SearchBarUI(hint = "Search For Cities in DB") { cityToSearch ->
                                    isInSearch = true
                                    searchQuery = cityToSearch
                                }
                            }
                            CityTable(dbStringList) {}
                        }
                    }
                }
            }

    }
}


