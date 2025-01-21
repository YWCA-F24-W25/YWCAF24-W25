package com.example.carownerapp.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carownerapp.Room.Car
import com.example.carownerapp.Room.Owner
import kotlinx.coroutines.launch

class DBViewModel(val appRepo: AppRepository) : ViewModel() {

    var dbOwnerList = mutableStateOf<List<Owner>>(emptyList())
    var dbCarsList = mutableStateOf<List<Car>>(emptyList())


    fun addOneOwnerToDB(o:Owner){
        viewModelScope.launch {
            appRepo.addNewOwner(o)
        }
    }
    fun getAllOwners(): List<Owner>{
        viewModelScope.launch {
            var list = appRepo.getAllOwner()
           dbOwnerList.value = list
        }
        return dbOwnerList.value
    }

    fun addCarToOwner(c:Car){
        viewModelScope.launch {
            appRepo.addCarToOwner(c)
        }
    }

    fun deleteOneCar(c:Car){
        viewModelScope.launch {
            appRepo.deleteOneCar(c)
        }
    }

    fun deleteOneOwner(o:Owner){
        viewModelScope.launch {
            appRepo.deleteOneOwner(o)
        }
    }


    fun getAllCars() : List<Car>{
        viewModelScope.launch {
            dbCarsList.value =  appRepo.getAllCars()
        }
        return dbCarsList.value
    }

    fun getAllCarsForOwner(o:Owner): List<Car>{

        viewModelScope.launch {
            var cars  = appRepo.getAllCarsForOwner(o)
            dbCarsList.value = cars
        }
        return dbCarsList.value
    }








}