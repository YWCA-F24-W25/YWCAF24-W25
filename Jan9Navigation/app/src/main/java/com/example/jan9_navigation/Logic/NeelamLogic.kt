package com.example.jan9_navigation.Logic

import android.content.Context
import android.widget.Toast


fun Calculate(expression:String,c: Context): Double {
    if( expression.isNullOrEmpty()){
        Toast.makeText(c,"Please enter number and operator to calculate", Toast.LENGTH_SHORT).show()
    }

    // Use regular expressions to split numbers and operators
    val numbers =
        expression.split("[+\\-*/]".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    val operators =
        expression.split("[0-9]+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()


    // Validate inputs
    require(numbers.size != 0) { "No numbers found in the expression" }


    // Parse first number as the initial result
    var result = numbers[0].toDouble()


    // Perform operations sequentially
    var operatorIndex = 1 // Operators split adds an empty string at the start
    for (i in 1 until numbers.size) {
        val num = numbers[i].toDouble()

        when (operators[operatorIndex]) {
            "+" -> result += num
            "-" -> result -= num
            "*" -> result *= num
            "/" -> {
                if (num == 0.0) {
                    throw ArithmeticException("Division by zero is not allowed")
                }
                result /= num
            }

            else -> throw IllegalArgumentException("Invalid operator: " + operators[operatorIndex])
        }

        operatorIndex++
    }

    return result
}

fun history(list: List<String>): String {
    var historyText = ""
    for (i in 0 until list.size) {
        historyText += list.get(i) + "\n"
    }
    return historyText

}
