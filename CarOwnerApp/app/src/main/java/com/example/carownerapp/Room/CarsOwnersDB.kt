package com.example.carownerapp.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Car::class, Owner::class], version = 1)
abstract class CarsOwnersDB : RoomDatabase(){
   abstract fun getDao():DBDao

    companion object {
        private var databaseInstance : CarsOwnersDB? = null
        // race condition == happened when multiple
        // threads trying to access same resources
        fun getDBInstance(context : Context): CarsOwnersDB {
            var inst = databaseInstance
            synchronized(this) {// no race condition
                if (inst == null) {
                    inst = Room.databaseBuilder(
                        context,
                        CarsOwnersDB::class.java,
                        "cars_owners_db"
                    ).build()
                }
                databaseInstance = inst
            }
            return inst!!
        }

    }


}