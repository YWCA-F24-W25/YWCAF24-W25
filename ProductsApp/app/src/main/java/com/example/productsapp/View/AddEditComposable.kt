package com.example.productsapp.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.productsapp.Model.Product


@Composable
fun AddEditProductUI(modifier: Modifier = Modifier,
                     onButtonClicked: (n:String, p:Double, q: Int)->Unit,
                     pName: String,
                     pPrice:String,
                     pQuantity: String

) {
    var nameInput = remember { mutableStateOf(pName) }
    var priceInput = remember { mutableStateOf(pPrice) }
    var quantityInput = remember { mutableStateOf(pQuantity) }

    Column (
        Modifier.fillMaxSize(),
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
                onButtonClicked(nameInput.value, p,q)
            }
        }) {
            Text("Save New Product")
        }


    }
}