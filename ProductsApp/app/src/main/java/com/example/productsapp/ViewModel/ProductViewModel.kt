package com.example.productsapp.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.productsapp.Model.Product

// note - this code works only if the ViewModel has no parameters
class ProductViewModel() : ViewModel() {

    var repo = AppRepository() // source of truth

    var stateList : SnapshotStateList<Product> = mutableStateListOf<Product>().apply {
        addAll(repo.getInitialStaticList())
    }

    fun addNewProduct(p:Product){
        repo.addNewProductStaticly(p)
        stateList.add(p)
    }






}