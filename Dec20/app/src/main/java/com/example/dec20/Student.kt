package com.example.dec20

 class allOperator {
    fun add(){}
    fun sub(){}
    fun times(){}
}


data class Student (val name: String,val number : Int) {

    // equal, toString, copy

    fun toUpperCaseName():String{
        return name.toUpperCase()
    }

}