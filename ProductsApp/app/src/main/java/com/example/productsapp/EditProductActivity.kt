package com.example.productsapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.productsapp.Model.Product
import com.example.productsapp.View.AddEditProductUI
import com.example.productsapp.ui.theme.ProductsAppTheme

class EditProductActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProductsAppTheme {
                var toeditProduct = intent.getSerializableExtra("toedit") as Product
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   AddEditProductUI(
                       modifier = Modifier.padding(innerPadding),
                       onButtonClicked = {name, price, quantity ->
                           val resultIntent = Intent()

                           resultIntent.putExtra("newname",name)
                           resultIntent.putExtra("newprice",price)
                           resultIntent.putExtra("newq",quantity)

                           setResult(RESULT_OK, resultIntent)
                           finish()
                       },
                       pName = toeditProduct.name,
                       pPrice = toeditProduct.price.toString(),
                       pQuantity = toeditProduct.quantity.toString()
                       )
                }
            }
        }
    }
}
