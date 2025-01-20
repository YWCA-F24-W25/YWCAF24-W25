package com.example.weatherapp.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class City(
    @PrimaryKey (autoGenerate = true)
    var id: Int,
    @ColumnInfo("city_name") var name: String
) {}