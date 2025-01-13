package com.example.productsapp.Model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable


data class Product(val id: Int,
                   var name: String,
                   var price: Double,
                   var quantity: Int) :  Serializable{}


class ProductService {
    var list = ArrayList<Product>()

    fun initList():  ArrayList<Product>{
        list.add(Product(1,"Hats",10.6, 30))
        list.add(Product(2,"T-shirts",20.9, 10))
        list.add(Product(3,"Pants",33.2, 10))
        return  list
    }

    fun addNewProduct(p: Product):ArrayList<Product>{
        list.add(p)
        return list
    }


    fun updateProduct(toeditProduct:Product, newName: String, newPrice: Double, newQuantity: Int){
        toeditProduct.name = newName
        toeditProduct.price = newPrice
        toeditProduct.quantity = newQuantity
    }
}

