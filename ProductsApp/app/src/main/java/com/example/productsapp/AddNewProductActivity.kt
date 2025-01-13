package com.example.productsapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.productsapp.Model.Product
import com.example.productsapp.ui.theme.ProductsAppTheme


class AddNewProductActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProductsAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AddNewProductUI(
                        modifier = Modifier.padding(innerPadding),
                        onSaveClicked = {newP ->
                            // new product is ready
                            val resultIntent = Intent()
                            resultIntent.putExtra("newProduct", newP)
                            setResult(RESULT_OK, resultIntent)
                            finish()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun AddNewProductUI( modifier: Modifier = Modifier, onSaveClicked: (p:Product)->Unit) {
    var nameInput = remember { mutableStateOf("") }
    var priceInput = remember { mutableStateOf("") }
    var quantityInput = remember { mutableStateOf("") }


    Column (Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ){
        TextField(
            value = nameInput.value,
            onValueChange = {
            nameInput.value = it
        },
            label = {
                Text("Enter Product Name")
            },
            placeholder = {
                Text("e.g. Hats")
            }

            )

        TextField(
            value = priceInput.value,
            onValueChange = {
                priceInput.value = it
            },
            label = {
                Text("Enter Product Price")
            },
            placeholder = {
                Text("e.g. 5.6")
            }

        )
        TextField(
            value = quantityInput.value,
            onValueChange = {
                quantityInput.value = it
            },
            label = {
                Text("Enter Product Quantity")
            },
            placeholder = {
                Text("e.g. 7")
            }

        )
        Button(onClick = {
           if ((!nameInput.value.isEmpty()) &&
               (!quantityInput.value.isEmpty()) &&
               (!priceInput.value.isEmpty())){
                   var q = quantityInput.value.toInt()
                    var p = priceInput.value.toDouble()
                    var newProduct = Product(Math.random().toInt(), nameInput.value, p, q)
                    onSaveClicked(newProduct)
               }
        }) {
            Text("Save New Product")
        }


   }
}