package com.example.weatherapp.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface CityDAO {

    // to insert new city to database
    // to delete city from database
    // search for cities start with letter 't'
    // edit city in my database

    @Insert
    suspend fun addNewCityToDB(c: City)

    @Delete
    suspend fun deleteOneCityFromDB(cityToDelete: City)

    @Update
    suspend fun updateOneCity(cityToUpdate: City)

    // select * from City === get all cities from database
    @Query("select * from City")
    fun getAllCitiesFromDB() : Flow<List<City>>

    // select * from City where cityName == "Toronto"
    @Query("select * from City where city_name LIKE '%' || :text || '%'")
    suspend fun getCitiesEqualsTo(text: String) : List<City>

    // select * from City where cityName == "Toronto"
    @Query("select * from City where city_name LIKE '%' || :text || '%'")
    fun secondFunctoToGetCitiesEqualsTo(text: String) : Flow<List<City>>


}