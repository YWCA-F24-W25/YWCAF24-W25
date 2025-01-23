package com.example.carownerapp.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow


@Dao
interface DBDao {

    @Query("select * from Owner")
    fun getAllOwners() : Flow<List<Owner>>

    @Query("select * from Car")
    fun getAllCars() : Flow<List<Car>>

    @Insert
    suspend fun insertOneOwner(o: Owner)

    @Delete
    suspend fun deleteOwner(o:Owner)

//    @Transaction
//    @Query("select * from Car, Owner where  Car.ownersID == Owner.id and Car.ownersID = :oID")
//    fun getAllCarsForOwner(oID:Int): Flow<List<Car>>// 1

    @Insert
    suspend fun addNewCarToOwner(newCar: Car)// ready with owner ID

    @Delete
    suspend fun deleteOneCar(c: Car)

    @Delete
    suspend fun deleteOneOwner(o:Owner)

    @Query("delete from Car where ownersID = :oID")
    suspend fun deleteAllCarsForOwner(oID: Int)

}