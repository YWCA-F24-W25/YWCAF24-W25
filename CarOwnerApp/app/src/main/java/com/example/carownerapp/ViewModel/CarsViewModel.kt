package com.example.carownerapp.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.carownerapp.Room.Car
import com.example.carownerapp.Room.CarsOwnersDB
import com.example.carownerapp.Room.Owner
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CarsViewModel (var applicationContext: Context) : ViewModel() {
    var db = CarsOwnersDB.getDBInstance(applicationContext)
    var appRepo = AppRepository(db)

    fun addCarToOwner(c: Car){
        viewModelScope.launch {
            appRepo.addCarToOwner(c)
        }
    }
    fun deleteOneCar(c:Car){
        viewModelScope.launch {
            appRepo.deleteOneCar(c)
        }
    }

    fun getOwnerName(id: Int): Flow<List<Owner>>{
       return appRepo.getOwnerName(id)
    }

    fun getAllCarsForOwner(oID: Int): Flow<List<Car>> {
        return appRepo.getAllCarsForOwner(oID)
    }




}