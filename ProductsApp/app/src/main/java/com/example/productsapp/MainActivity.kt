package com.example.productsapp

import android.annotation.SuppressLint
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
    private var editLauncher: ActivityResultLauncher<Intent>? = null
    var selectedProductID = -1
  //  lateinit var selecedProduct : Product

    lateinit var vm : Lazy<ProductViewModel>
    @SuppressLint("SuspiciousIndentation")
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
        editLauncher = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                val data = result.data

                var nn = data?.getStringExtra("newname")
                var np = data?.getDoubleExtra("newprice",0.0)
                var nq = data?.getIntExtra("newq",0)

                if (nn != null) {
                    if (np != null) {
                        if (nq != null) {
                            var list = vm.value.stateList.filter { p ->
                                p.id == selectedProductID
                            }
                           var i = vm.value.stateList.indexOf(list[0])
                            vm.value.updateOneProduct(vm.value.stateList[i],nn,np,nq)
                        }
                    }
                }
            }
        }
        setContent {
            ProductsAppTheme {
                vm = viewModels<ProductViewModel>()// connection between UI and VM
                AppUI(
                    vm.value.stateList,
                    addNewProductClicked = {
                    var intent = Intent(this,AddNewProductActivity::class.java)
                    myLauncher!!.launch(intent)
                }, oneProductClicked = {
                    sp ->
                    //selecedProduct = sp
                        selectedProductID = sp.id
                    var intent = Intent(this, EditProductActivity::class.java)
                       intent.putExtra("toedit",sp)
                        editLauncher!!.launch(intent)

                    })
            }
        }
    }
}

