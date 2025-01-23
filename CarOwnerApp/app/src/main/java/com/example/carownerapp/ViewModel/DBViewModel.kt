package com.example.carownerapp.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carownerapp.Room.Car
import com.example.carownerapp.Room.Owner
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DBViewModel(val appRepo: AppRepository) : ViewModel() {

//    var dbOwnerList = mutableStateOf<List<Owner>>(emptyList())
//    var dbCarsList = mutableStateOf<List<Car>>(emptyList())
//

    fun addOneOwnerToDB(o:Owner){
        viewModelScope.launch {
            appRepo.addNewOwner(o)
        }
    }
    fun getAllOwners(): Flow<List<Owner>> {
        return appRepo.getAllOwner()
    }



    fun searchForOwner(name:String): Flow<List<Owner>>{
        return appRepo.searchForOwner(name)
    }



    fun deleteOneOwner(o:Owner){
        viewModelScope.launch {
            appRepo.deleteOneOwner(o)
        }
    }

    fun getAllCars() : Flow<List<Car>>{
           return  appRepo.getAllCars()
    }


    fun getAllCarsForOwner(oID: Int): Flow<List<Car>> {
        return appRepo.getAllCarsForOwner(oID)
    }
    fun deleteOneCar(c:Car){
        viewModelScope.launch {
            appRepo.deleteOneCar(c)
        }
    }









}