package com.example.productsapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.productsapp.View.AppUI
import com.example.productsapp.ViewModel.ProductViewModel
import com.example.productsapp.ui.theme.ProductsAppTheme

class MainActivity : ComponentActivity() {
    lateinit var vm : Lazy<ProductViewModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProductsAppTheme {
                vm = viewModels<ProductViewModel>()// connection between UI and VM
                AppUI( vm.value.stateList, addNewProductClicked = {
                    var intent = Intent(this,AddNewProductActivity::class.java)
                    startActivity(intent)
                })
            }
        }
    }
}

