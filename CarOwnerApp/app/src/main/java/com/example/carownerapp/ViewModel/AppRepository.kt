package com.example.carownerapp.ViewModel

import com.example.carownerapp.Room.Car
import com.example.carownerapp.Room.CarsOwnersDB
import com.example.carownerapp.Room.Owner

class AppRepository(var db: CarsOwnersDB) {


    var dao = db.getDao()

    suspend fun addNewOwner(o:Owner){
        dao.insertOneOwner(o)// to RoomDB
        ///                     to firebase
    }


    suspend fun getAllOwner(): List<Owner>{
       return dao.getAllOwners()
    }

    suspend fun addCarToOwner(c: Car){
        return dao.addNewCarToOwner(c)
    }


    suspend fun getAllCarsForOwner(o:Owner) : List<Car>{
        var carOwnerObject =  dao.getAllCarsForOwner(o.id)//
        return carOwnerObject.ownersCars
    }


    suspend fun deleteOneCar(c:Car){
        dao.deleteOneCar(c)
    }

    suspend fun deleteOneOwner(o:Owner){
        dao.deleteOwner(o)
    }


    suspend fun getAllCars(): List<Car>{
        return dao.getAllCars()
    }
}