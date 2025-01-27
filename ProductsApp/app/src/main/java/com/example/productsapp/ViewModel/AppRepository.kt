package com.example.productsapp.ViewModel

import com.example.productsapp.Model.FirebaseInstance
import com.example.productsapp.Model.Product
import kotlinx.coroutines.tasks.await

class AppRepository {
    private val firestoreDB = FirebaseInstance.database

    // add new product to firestore
    // update one product
    // delete one product
    // get all products from firestore


    suspend fun searchForProductsCheaperThanValueFromCloudDatabase(price: Double): List<Product> {
        var allProducts = emptyList<Product>()

        var alldocuments = firestoreDB.collection("Products")
            .whereLessThan("price",price).
        get().
        await()
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


    suspend fun getAllProductsFromCloudDatabase(): List<Product> {
        var allProducts = emptyList<Product>()
        var alldocuments = firestoreDB.collection("Products").
        get().
        await()
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

    suspend fun addNewDocumentToCloudDatabase(name: String, price: Double, q: Int):Boolean{
        val newproductData = hashMapOf(
            "name" to name,
            "price" to price,
            "quantity" to q
        )
        return try {
            firestoreDB.collection("Products").add(newproductData).await()
            true
        }catch (e:Exception){
            false
        }
    }

    suspend fun updateDocumentInCloudDatabase(docID: String,name: String, price: Double, q: Int):Boolean{
        val updatedDoc = hashMapOf(
            "name" to name,
            "price" to price,
            "quantity" to q
        )
        return try {
            var docRef = firestoreDB.collection("Products").document(docID)
            docRef.update(updatedDoc as Map<String, Any>).await()
            true
        }catch (e:Exception){
            false
        }
    }

    suspend fun deleteOneDocumentFromCloudDatabase(docID: String):Boolean{
        return try {
            var docRef = firestoreDB.collection("Products").document(docID)
            docRef.delete().await()
            true
        }catch (e:Exception){
            false
        }
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


