package com.example.weatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import com.example.weatherapp.View.WeatherUI

import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.ViewModel.AppRepository
import com.example.weatherapp.ViewModel.ViewModelFactory
import com.example.weatherapp.ViewModel.WeatherViewModel
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng


class WeatherInLocationActivity : ComponentActivity() {

        val locationPermissionRequestLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            if (permissions[android.Manifest.permission.ACCESS_FINE_LOCATION] == true &&
                (permissions[android.Manifest.permission.ACCESS_COARSE_LOCATION] == true)
            ) {

                Toast.makeText(this, "Location Granted",Toast.LENGTH_LONG).show()
                // we got the needed permission
            } else {
                Toast.makeText(this, "Location IS not Granted",Toast.LENGTH_LONG).show()

                // no permission
            }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        locationPermissionRequestLauncher.launch(
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
            setContent {
                var appRepo = AppRepository()
                var weatherViewModelFactory = ViewModelFactory(appRepo)
                var wvm = ViewModelProvider(this,weatherViewModelFactory)[WeatherViewModel::class.java]


                WeatherAppTheme {
                    Scaffold(
                        topBar = { MyTopBar() },
                        modifier = Modifier.fillMaxSize()
                    ) { innerPadding ->
                        LocationUI(
                            modifier = Modifier.padding(innerPadding),wvm
                        )
                    }
                }
            }
        }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar() {
    var cnx = LocalContext.current
    TopAppBar(title = {
        Text("Current location")
    }, actions = {
        IconButton(onClick = {
            var intent = Intent(cnx, SearchActivity::class.java)
            cnx.startActivity(intent)
        }) {
            Icon(Icons.Default.Search, "")
        }    })
}

@SuppressLint("MissingPermission")
@Composable
fun LocationUI( modifier: Modifier = Modifier, wvm: WeatherViewModel) {
   // var locationData by remember {LocationServices.get}
    var context = LocalContext.current
    var userLocation =  remember { mutableStateOf<LatLng?>(null) }
    var fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    val locationRequest = remember {
        LocationRequest.create().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 10000 // repeat this request every 10 second
            fastestInterval = 5000
        }
    }
    LaunchedEffect(Unit) {
        var locationCallBack = object :LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult) {
              locationResult.lastLocation.let {
                  location ->
                  val newLocation = location?.let { LatLng(it.latitude,location.longitude)}
                      userLocation.value = newLocation
                    wvm.getWeatherForLocation(userLocation.value?.latitude.toString(),userLocation.value?.longitude.toString())
              }
              }
            }
                fusedLocationClient.requestLocationUpdates(
                    locationRequest,locationCallBack, context.mainLooper )
        }
    wvm.apiWeatherObject?.weather?.get(0)?.let { Log.d("icon", it.icon) }
    WeatherUI(modifier, "Latitude:  " + (userLocation.value?.latitude ?: 0) +
            "Longitude:  " +  (userLocation.value?.longitude ?: 0),wvm.apiWeatherObject)
}
