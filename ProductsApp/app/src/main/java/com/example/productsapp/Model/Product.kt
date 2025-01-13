package com.example.productsapp.Model



class Product(i: Int, s: String, d: Double, i1: Int) {
    var id: Int = 0
    var name: String = ""
    var price: Double = 0.0
    var quentity : Int = 0
}

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

