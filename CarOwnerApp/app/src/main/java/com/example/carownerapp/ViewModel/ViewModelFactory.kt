package com.example.carownerapp.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras

class ViewModelFactory(private val appRepo : AppRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(DBViewModel::class.java)){
            return DBViewModel(appRepo) as T
        }
        else {
            throw IllegalArgumentException("Not matching class")
        }
    }
}

class CarsViewModelFactory(private val context: Context) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(CarsViewModel::class.java)){
            return CarsViewModel(context) as T
        }
        else {
            throw IllegalArgumentException("Not matching class")
        }
    }
}