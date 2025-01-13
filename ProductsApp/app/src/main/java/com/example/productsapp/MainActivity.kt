package com.example.productsapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.productsapp.Model.Product
import com.example.productsapp.View.AppUI
import com.example.productsapp.ViewModel.ProductViewModel
import com.example.productsapp.ui.theme.ProductsAppTheme


class MainActivity : ComponentActivity() {

    private var myLauncher: ActivityResultLauncher<Intent>? = null
    lateinit var vm : Lazy<ProductViewModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        myLauncher = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                val data = result.data
                var newProduct = data?.getSerializableExtra("newProduct") as Product
                if (newProduct != null) {
                    vm.value.addNewProduct(newProduct)
                }
            }
        }
        setContent {
            ProductsAppTheme {
                vm = viewModels<ProductViewModel>()// connection between UI and VM
                AppUI( vm.value.stateList, addNewProductClicked = {
                    var intent = Intent(this,AddNewProductActivity::class.java)
                    myLauncher!!.launch(intent)
                })
            }
        }
    }
}

