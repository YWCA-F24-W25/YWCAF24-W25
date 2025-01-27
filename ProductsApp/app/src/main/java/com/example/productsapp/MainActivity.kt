package com.example.productsapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.productsapp.Model.Product
import com.example.productsapp.View.AppUI
import com.example.productsapp.View.SearchBarUI
import com.example.productsapp.ViewModel.ProductViewModel
import com.example.productsapp.ui.theme.ProductsAppTheme
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


class MainActivity : ComponentActivity() {
// MVVM
    private var myLauncher: ActivityResultLauncher<Intent>? = null
    private var editLauncher: ActivityResultLauncher<Intent>? = null
    var selectedProductDocID = ""

    lateinit var vm : Lazy<ProductViewModel>

    @SuppressLint("SuspiciousIndentation", "UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        var isInSearch by mutableStateOf(false)

        myLauncher = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                val data = result.data

                var newProductName = data?.getStringExtra("name")
                var newProductPrice = data?.getDoubleExtra("price",0.0)
                var newProductQuantity = data?.getIntExtra("quantity",0)

                if (newProductName != null) {
                    if (!newProductName.isEmpty()) {
                        if (newProductPrice != null) {
                            if (newProductQuantity != null) {
                               vm.value.addNewProductToDB(newProductName,newProductPrice,newProductQuantity)
                            }
                        }
                    }
                }
            }
        }
        editLauncher = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                val data = result.data
                var newProductName = data?.getStringExtra("name")
                var newProductPrice = data?.getDoubleExtra("price",0.0)
                var newProductQuantity = data?.getIntExtra("quantity",0)
                if (newProductName != null) {
                    if (newProductPrice != null) {
                        if (newProductQuantity != null) {
                            vm.value.updateOneDocInDB(
                                selectedProductDocID,
                                newProductName,
                                newProductPrice,
                                newProductQuantity)
                        }
                    }
                }
            }
        }
        setContent {
            ProductsAppTheme {
                var context = LocalContext.current

                vm = viewModels<ProductViewModel>()// connection between UI and VM
               if (!isInSearch) {
                   vm.value.getAllProductsFromDB()
               }
                Column {
                    SearchBarUI("Enter a price filter") { price ->
                        if (price.length >= 1) {
                            isInSearch = true
                            var priceV = price.toDouble()
                            vm.value.filterProducts(priceV)
                        }else {
                            vm.value.getAllProductsFromDB()
                        }
                    }
                    AppUI(
                        vm.value.stateList,
                        addNewProductClicked = {
                            var intent = Intent(context, AddNewProductActivity::class.java)
                            myLauncher!!.launch(intent)
                        }, oneProductClicked = { product ->
                            selectedProductDocID = product.id
                            var intent = Intent(context, EditProductActivity::class.java)
                            intent.putExtra("toedit", product)
                            editLauncher!!.launch(intent)
                        }, oneProductToDelete = {
                            // alert
                            vm.value.deleteOneDocument(it.id)
                        })
                }
            }
        }
    }
}

