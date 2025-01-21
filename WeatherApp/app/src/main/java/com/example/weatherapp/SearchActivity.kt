package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.Room.City
import com.example.weatherapp.Room.CityDatabase
import com.example.weatherapp.View.AlertComposable
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

        var database = CityDatabase.getDBInstance(this)
        var cityDao = database.getCityDao()
        var appRepo = AppRepository(cityDao)
        var cityViewModelFactory = ViewModelFactory(appRepo)
        var vm = ViewModelProvider(this,cityViewModelFactory)[CityViewModel::class.java]



        setContent {
            // This default initalizer only works if the ViewModel has no parameter
           // vm = viewModels<CityViewModel>()

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun APPBody(vm: CityViewModel, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var selectedCity = remember { mutableStateOf("") }
    var showAlert = remember { mutableStateOf(false) }
   Column (modifier = Modifier.fillMaxSize()) {
       Row (modifier = Modifier.
       fillMaxHeight(0.15f),
           verticalAlignment = Alignment.CenterVertically,
           horizontalArrangement = Arrangement.SpaceBetween
       ) {
           IconButton(onClick = {
               var intent = Intent(context,FavoriteCitiesActivity::class.java)
               context.startActivity(intent)
           }) {
               Icon(Icons.Default.Favorite, contentDescription = "tofav",modifier = Modifier.fillMaxSize(0.4f))
           }
           SearchBarUI(
               hint ="Search for cities with 3 chars",
               searchForCity = {
                   if (it.isEmpty()){
                       vm.setList()
                   }else {
                       vm.getAllCitiesFromAPI(it)
                   }
               })
       }
      Row{
          CityTable(vm.apiListOfCities,
              onOneCitySelected = { city ->
                  showAlert.value = true
                  selectedCity.value = city

          })

        if (showAlert.value){
            AlertComposable(selectedCity.value,
                message = "Do You Want To Save ${selectedCity.value} to DB?",
                okButton = "Yes, Save",
                noButton = "NO, Don't Save"
                , onSave = {
                  var id = Math.random().toInt()
                  vm.insertNewCityInDB(City(id, selectedCity.value))
                  var intent = Intent(context,WeatherActivity::class.java)
                  intent.putExtra("city",selectedCity.value)
                  context.startActivity(intent)
                showAlert.value = false
              }, onNotSave = {
                  var intent = Intent(context,WeatherActivity::class.java)
                   intent.putExtra("city",selectedCity.value)
                 context.startActivity(intent)
                showAlert.value = false
              } )
        }

//          if (isInDB.value == true){
//              AlertComposable(city = selectedCity.value,
//                  message = "This City Is Already in your Fav List!!!",
//                  okButton = "OK",
//                  noButton = "", onSave = {
//                      isInDB.value = false
//                  }, onNotSave = {
//                      isInDB.value = false
//                  })
//          }



      }

   }
}
