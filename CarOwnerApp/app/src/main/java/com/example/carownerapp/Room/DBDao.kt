package com.example.carownerapp.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction


@Dao
interface DBDao {

    @Query("select * from Owner")
    suspend fun getAllOwners() : List<Owner>


    @Query("select * from Car")
    suspend fun getAllCars() : List<Car>



    @Insert
    suspend fun insertOneOwner(o: Owner)

    @Delete
    suspend fun deleteOwner(o:Owner)

    @Transaction
    @Query("select * from Car, Owner where  Car.ownersID == Owner.id and Car.ownersID = :oID")
    suspend fun getAllCarsForOwner(oID:Int): CarsAndOwners// 1

    @Insert
    suspend fun addNewCarToOwner(newCar: Car)// ready with owner ID

    @Delete
    suspend fun deleteOneCar(c: Car)

    @Delete
    suspend fun deleteOneOwner(o:Owner)

    @Query("delete from Car where ownersID = :oID")
    suspend fun deleteAllCarsForOwner(oID: Int)

}