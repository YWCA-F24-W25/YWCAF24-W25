package com.example.productsapp.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.productsapp.Model.Product
import java.io.Serializable

// note - this code works only if the ViewModel has no parameters
class ProductViewModel() : ViewModel() {

    var repo = AppRepository() // source of truth

    var stateList : SnapshotStateList<Product> = mutableStateListOf<Product>().apply {
            addAll(repo.getInitialStaticList())
        }


    fun addNewProduct(p: Product){
        repo.addNewProductStaticly(p)
        stateList.add(p)
    }

    fun updateOneProduct(p:Product, nname:String, nprice: Double, nquantity: Int) {
        // update the list in my model
        repo.updateExistingProduct(p,nname,nprice,nquantity)

        // update the list for my UI
      var index =  stateList.toList().indexOf(p)
       p.name = nname
        p.price = nprice
        p.quantity = nquantity
        
        stateList.set(index, p)



    }





}