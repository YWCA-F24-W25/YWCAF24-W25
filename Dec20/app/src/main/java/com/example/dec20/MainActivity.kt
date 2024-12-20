package com.example.dec20

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
// variables in kotlin
    // classes
    // loops
    // functions
    // closur = lambda = inline function = function with no name
    fun myHigherOrderFunction(a: Int, b:Int, op: (Int,Int) -> Int) : Int{
        return op(a,b)
    }
    fun addFun (n: Int, b:Int) = n + b
    fun subFun (n: Int, b:Int) = n - b
    fun timesFun (n: Int, b:Int) = n * b
    fun divFun (n: Int, b:Int) = n / b

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val addbut = findViewById<Button>(R.id.add)
        val subbut = findViewById<Button>(R.id.sub)
        val timesbut = findViewById<Button>(R.id.times)
        val dividbut = findViewById<Button>(R.id.divid)
        val num1 = findViewById<EditText>(R.id.num1)
        val num2 = findViewById<EditText>(R.id.num2)
        val resultText = findViewById<TextView>(R.id.result)

        addbut.setOnClickListener{
            if ((!num1.text.toString().isEmpty()) && (!num2.text.toString().isEmpty() )){
                val n1 = num1.text.toString().toInt()
                val n2 = num2.text.toString().toInt()

                resultText.text = myHigherOrderFunction(n1, n2, ::addFun).toString()
            }
        }

        subbut.setOnClickListener{
            if ((!num1.text.toString().isEmpty()) && (!num2.text.toString().isEmpty() )){
                val n1 = num1.text.toString().toInt()
                val n2 = num2.text.toString().toInt()
                resultText.text = myHigherOrderFunction(n1, n2, ::subFun).toString()
            }
        }
        timesbut.setOnClickListener{
            if ((!num1.text.toString().isEmpty()) && (!num2.text.toString().isEmpty() )){
                val n1 = num1.text.toString().toInt()
                val n2 = num2.text.toString().toInt()
                resultText.text = myHigherOrderFunction(n1, n2, ::timesFun).toString()
            }
        }
        dividbut.setOnClickListener{
            if ((!num1.text.toString().isEmpty()) && (!num2.text.toString().isEmpty() )){
                val n1 = num1.text.toString().toInt()
                val n2 = num2.text.toString().toInt()
                resultText.text = myHigherOrderFunction(n1, n2, ::divFun).toString()
            }
        }








        val functionButton = findViewById<Button>(R.id.functionsinKotlin)

        functionButton.setOnClickListener{
                val result = sum(40,50)
                Toast.makeText(this, "The sume result of 40 and 50 is $result",Toast.LENGTH_LONG).show()
        }

    val loopButton = findViewById<Button>(R.id.loopsinKotlin)
    loopButton.setOnClickListener {
        val numbers : List<Int> = listOf(2,5,-1,44,21)
        for (num in numbers){
            Toast.makeText(this, "number = $num",Toast.LENGTH_SHORT).show()
        }
    }

    val lambdaSum = { a: Int, b: Int -> a + b}//
    val lambdaAve = {a: Double, b: Double, c: Double -> (a + b + c)/3 }


    val lambdaButton = findViewById<Button>(R.id.lambdainKotlin)
    lambdaButton.setOnClickListener{
        val result = lambdaSum(40,50)
        Toast.makeText(this, "The Lambda result of 40 and 50 is $result",Toast.LENGTH_LONG).show()
    }

    // map , filter, count, reduce ==> Higher order functions because they have lambda as paremeter.
    // only for collections
    // map as data structure == > key - value pairs

    val filterButton = findViewById<Button>(R.id.filterFunctioninKotlin)
    filterButton.setOnClickListener{
       val numbers = arrayOf(1,4,-3,-9,2,-1,7)

       val positivNumbers =  numbers.filter{ it > 0}
        val evenNumbers = numbers.filter { it % 2 == 0 }
        // it similar to this in java

        for (num in positivNumbers){
            Toast.makeText(this, "number = $num",Toast.LENGTH_SHORT).show()
        }
    }

    val mapButton = findViewById<Button>(R.id.mapFunctioninKotlin)
    mapButton.setOnClickListener{
        val numbers = arrayOf(1,4,-3,-9,2,-1,7)
        val newNumbers = numbers.map{ it * 2}
        val stringarray = numbers.map{ "The number is $it"}
        val halfNumbers =  numbers.map{ it / 2}
        for (num in halfNumbers){
            Toast.makeText(this, "$num",Toast.LENGTH_SHORT).show()
        }
    }
    }



       fun sum(a: Int, b: Int) : Int{
           return  a + b;
       }


    }