package com.example.productsapp.Model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable


data class Product(val id: Int,
                   val name: String,
                   val price: Double,
                   val quantity: Int) :  Serializable{}


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
}

