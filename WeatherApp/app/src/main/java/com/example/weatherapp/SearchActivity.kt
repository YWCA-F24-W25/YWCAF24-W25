package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.View.CityTable
import com.example.weatherapp.View.SearchBarUI
import com.example.weatherapp.ViewModel.AppRepository
import com.example.weatherapp.ViewModel.CityViewModel
import com.example.weatherapp.ViewModel.ViewModelFactory
import com.example.weatherapp.ui.theme.WeatherAppTheme

class SearchActivity : ComponentActivity() {
   // lateinit var vm : Lazy<CityViewModel>

    // if I need the app context to create the App Repo,
    // I can't create it in the View Model.
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // This default initalizer only works if the ViewModel has no parameter
           // vm = viewModels<CityViewModel>()
            var appRepo = AppRepository()
            var cityViewModelFactory = ViewModelFactory(appRepo)
            var vm = ViewModelProvider(this,cityViewModelFactory)[CityViewModel::class.java]

            WeatherAppTheme {
                Scaffold( modifier = Modifier.fillMaxSize()
                    ) {
                    innerPadding ->
                    APPBody(
                        vm,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun APPBody(vm: CityViewModel, modifier: Modifier = Modifier) {
    val context = LocalContext.current
   Column (modifier = Modifier.fillMaxSize()) {
       Row (modifier = Modifier.fillMaxHeight(0.15f)) {
           SearchBarUI(searchForCity = {
               if (it.isEmpty()){
                   vm.setList()
               }else {
                   vm.getAllCitiesFromAPI(it)
               }
               })
       }
      Row{
          CityTable(vm.apiListOfCities, onOneCitySelected = { city ->
              // go to weather activity and fetch weather data
                var intent = Intent(context,WeatherActivity::class.java)
                intent.putExtra("city",city)
              Log.d("Selected City",city)
              context.startActivity(intent)
          })
      }

   }
}
