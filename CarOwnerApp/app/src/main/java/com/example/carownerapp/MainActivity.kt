package com.example.carownerapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.carownerapp.Room.Car
import com.example.carownerapp.Room.CarsOwnersDB
import com.example.carownerapp.Room.Owner
import com.example.carownerapp.ViewModel.AppRepository
import com.example.carownerapp.ViewModel.DBViewModel
import com.example.carownerapp.ViewModel.ViewModelFactory
import com.example.carownerapp.ui.theme.CarOwnerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var db = CarsOwnersDB.getDBInstance(this)
        var appRepo = AppRepository(db)
        var vmFactory = ViewModelFactory(appRepo)
        var vm = ViewModelProvider(this,vmFactory)[DBViewModel::class.java]

      //  var id = Math.random().toInt()
      //  vm.addOneOwnerToDB(Owner(id,"John Lee", 2000))

//        id = Math.random().toInt()
//        vm.addOneOwnerToDB(Owner(id,"Mary Smith", 1980))
//
//         id = Math.random().toInt()
//        vm.addCarToOwner(Car(id,"Nissan",2013,5))
//
//        id = Math.random().toInt()
//        vm.addCarToOwner(Car(id,"Mazda",2024,5))

//        id = Math.random().toInt()
//        vm.addCarToOwner(Car(id,"Kia",2019,2))



        enableEdgeToEdge()
        setContent {
          var  list =  vm.getAllOwners()

            if (list.size> 0){
              var carList =  vm.getAllCarsForOwner(list[0])
            }
            CarOwnerAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CarOwnerAppTheme {
        Greeting("Android")
    }
}