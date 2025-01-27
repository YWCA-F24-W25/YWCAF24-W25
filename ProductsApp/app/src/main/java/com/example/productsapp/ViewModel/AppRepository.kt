package com.example.productsapp.ViewModel

import android.annotation.SuppressLint
import android.util.Log
import com.example.productsapp.Model.FirebaseInstance
import com.example.productsapp.Model.Product
import com.example.productsapp.Model.ProductInterface
import com.example.productsapp.Model.ProductService
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

class AppRepository {

    var staticProductService = ProductService()
    private val firestoreDB = FirebaseInstance.database

    // add new product to firestore
    // update one product
    // delete one product
    // get all products from firestore


    suspend fun getAllProductsFromCloudDatabase(): List<Product> {
        var allProducts = emptyList<Product>()
        var alldocuments = firestoreDB.collection("Products").get().await()
        for (document in alldocuments) {
            allProducts += Product(
                document.id,
                document.data["name"] as String,
                document.data["price"] as Double,
                (document.data["quantity"] as Long).toInt()
            )
        }
        return allProducts
    }
}


//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    allProducts += Product(document.id,
//                        document.data["name"] as String,
//                        document.data["price"] as Double,
//                        (document.data["quantity"] as Long).toInt()
//                        )
//                }
//            }
//            .addOnFailureListener { exception ->



//    override fun getInitialStaticList(): ArrayList<Product> {
//        return staticProductService.initList()
//    }

//    override fun addNewProductStaticly(p: Product): ArrayList<Product> {
//        return staticProductService.addNewProduct(p)
//    }
//
//    override fun updateExistingProduct(newp: Product) {
//        staticProductService.updateProduct(newp)
//    }


