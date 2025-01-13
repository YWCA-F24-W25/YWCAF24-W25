package com.example.productsapp.ViewModel

import com.example.productsapp.Model.Product
import com.example.productsapp.Model.ProductInterface
import com.example.productsapp.Model.ProductService

class AppRepository :  ProductInterface{

    var staticProductService = ProductService()

    override fun getInitialStaticList(): ArrayList<Product> {
        return staticProductService.initList()
    }

    override fun addNewProductStaticly(p: Product): ArrayList<Product> {
        return staticProductService.addNewProduct(p)
    }

    override fun updateExistingProduct(p: Product, name: String, price: Double, q: Int) {
        staticProductService.updateProduct(p,name,price,q)
    }


}