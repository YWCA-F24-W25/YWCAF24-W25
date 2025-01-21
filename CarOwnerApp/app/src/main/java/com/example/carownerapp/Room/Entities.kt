package com.example.carownerapp.Room

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
@Entity
data class Car(
                @PrimaryKey(autoGenerate = true)
                val id: Int,
               val model: String,
               var year: Int ,
                val ownersID: Int
    ){
}
@Entity
data class Owner(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val fullname: String,
    @ColumnInfo("yearOfBirth")
    var yob: Int ){
}

data class CarsAndOwners (
    @Embedded val owner: Owner,
    @Relation(parentColumn = "id",
                entityColumn = "ownersID")
    val ownersCars : List<Car>
)