package com.example.weatherapp.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [City::class], version = 1)
abstract class CityDatabase : RoomDatabase() {
    abstract fun getCityDao():CityDAO
    // write here the code to init the database
    // done in background thread

    companion object {
        private var databaseInstance : CityDatabase? = null
        // race condition == happened when multiple
        // threads trying to access same resources
        fun getDBInstance(context : Context): CityDatabase {
            var inst = databaseInstance
          synchronized(this) {// no race condition
              if (inst == null) {
                  inst = Room.databaseBuilder(
                      context,
                      CityDatabase::class.java,
                      "citiesDB"
                  ).build()
              }
              databaseInstance = inst
          }
            return inst!!
        }

    }


}