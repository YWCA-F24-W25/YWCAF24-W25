package com.example.productsapp.Model

interface ProductInterface {
    fun getInitialStaticList():ArrayList<Product>
    fun addNewProductStaticly(p:Product): ArrayList<Product>
}