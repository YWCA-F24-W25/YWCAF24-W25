package com.example.productsapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.productsapp.Model.Product
import com.example.productsapp.View.AddEditProductUI
import com.example.productsapp.ui.theme.ProductsAppTheme

class AddNewProductActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProductsAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AddEditProductUI(
                        modifier = Modifier.padding(innerPadding),
                        onButtonClicked = {name, price, quantity ->
                            val resultIntent = Intent()
                            resultIntent.putExtra("name",name)
                            resultIntent.putExtra("price",price)
                            resultIntent.putExtra("quantity",quantity)
                            setResult(RESULT_OK, resultIntent)
                            finish()
                        },
                        pName = "",
                        pPrice = "",
                        pQuantity = ""
                    )
                }
            }
        }
    }
}
