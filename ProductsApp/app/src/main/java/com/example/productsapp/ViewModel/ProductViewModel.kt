package com.example.productsapp.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productsapp.Model.Product
import kotlinx.coroutines.launch
import java.io.Serializable

// note - this code works only if the ViewModel has no parameters
class ProductViewModel() : ViewModel() {

    var repo : AppRepository = AppRepository()// source of truth
    var stateList by mutableStateOf<List<Product>>(emptyList())

    fun getAllProductsFromDB():List<Product>{
        viewModelScope.launch{
           stateList = repo.getAllProductsFromCloudDatabase()
        }
        return stateList
    }

    fun filterProducts(p: Double):List<Product>{
        viewModelScope.launch{
            stateList = repo.searchForProductsCheaperThanValueFromCloudDatabase(p)
        }
        return stateList
    }

    fun addNewProductToDB(name: String, price: Double, q: Int): Boolean{
        var done = false
        viewModelScope.launch{
            done = repo.addNewDocumentToCloudDatabase(name,price,q)
            stateList = repo.getAllProductsFromCloudDatabase()
        }
        return done
    }


    fun updateOneDocInDB(docID: String,name: String, price: Double, q: Int): Boolean{
        var done = false
        viewModelScope.launch{
            done = repo.updateDocumentInCloudDatabase(docID,name,price,q)
            stateList = repo.getAllProductsFromCloudDatabase()
        }
        return done
    }

    fun deleteOneDocument(docID: String): Boolean{
        var done = false
        viewModelScope.launch{
            done = repo.deleteOneDocumentFromCloudDatabase(docID)
            stateList = repo.getAllProductsFromCloudDatabase()
        }
        return done
    }
}