package com.example.carownerapp.ViewModel

import androidx.compose.runtime.collectAsState
import com.example.carownerapp.Room.Car
import com.example.carownerapp.Room.CarsOwnersDB
import com.example.carownerapp.Room.Owner
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

class AppRepository(var db: CarsOwnersDB) {


    var dao = db.getDao()

    suspend fun addNewOwner(o:Owner){
        dao.insertOneOwner(o)// to RoomDB
        ///                     to firebase
    }


    fun getAllOwner(): Flow<List<Owner>> {
       return dao.getAllOwners()
    }

    suspend fun addCarToOwner(c: Car){
        return dao.addNewCarToOwner(c)
    }


    fun getAllCarsForOwner(oid:Int) : Flow<List<Car>>{
       // map --> convert one list to another list

        return dao.getAllCars().map{ cars ->
            cars.filter { car ->
                car.ownersID == oid
            }
        }
    }

    fun getOwnerName(id: Int): Flow<List<Owner>>{
        return dao.getAllOwners().map{ owners ->
            owners.filter {owner ->
                owner.id == id
            }
        }
    }

    fun searchForOwner(name: String) : Flow<List<Owner>>{

//        var mynumbers : List<Int> = listOf(4,33,-4, 11, -3)
//        var onlyPositive =  mynumbers.filter { num ->
//            num > 0
//        }
//        var doubleList = mynumbers.map{ num ->
//            num.toString()
//        }

        // Flow<List<Owner>> // stream of List // multiple List of Owner
//       var emptyLists = dao.getAllOwners().filter { owners ->
//            owners.isEmpty()
//        }

        return dao.getAllOwners().map {
            owners ->
            owners.filter { owner ->
                owner.fullname == name
            }
        }
    }




    suspend fun deleteOneCar(c:Car){
        dao.deleteOneCar(c)
    }

    suspend fun deleteOneOwner(o:Owner){


        dao.deleteOwner(o)

    }


    fun getAllCars(): Flow<List<Car>>{
        return dao.getAllCars()
    }
}