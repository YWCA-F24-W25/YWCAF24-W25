package com.example.productsapp.Model

import java.io.Serializable


data class Product(
    val id: String,
                   var name: String,
                   var price: Double,
                   var quantity: Int) :  Serializable{}


class ProductService {
    var list = ArrayList<Product>()
//    fun initList():  ArrayList<Product>{
//        list.add(Product(1,"Hats",10.6, 30))
//        list.add(Product(2,"T-shirts",20.9, 10))
//        list.add(Product(3,"Pants",33.2, 10))
//        return  list
//    }
    fun addNewProduct(p: Product):ArrayList<Product>{
        list.add(p)
        return list
    }
    fun updateProduct(toeditProduct:Product){
        val index = list.indexOfFirst { it.id == toeditProduct.id }
        if (index != -1) {
            list[index] = toeditProduct  // Update the task in the UI
        }
    }


}

