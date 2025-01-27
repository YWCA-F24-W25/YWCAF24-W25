package com.example.productsapp.View

import android.annotation.SuppressLint
import android.widget.ImageButton
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.productsapp.Model.Product
import com.example.productsapp.ViewModel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppUI( list: List<Product>,
           addNewProductClicked: ()->Unit,
           oneProductClicked: (p: Product)->Unit,
           oneProductToDelete: (p:Product)->Unit
           ){

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = {
                Text("Product List")
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {addNewProductClicked()}) {
                Icon(Icons.Default.Add, contentDescription = "add")
            }
        }
    ) { innerPadding ->
        LazyColumn (
            modifier = Modifier.padding(innerPadding)
        ) {
            items(count = list.size){
                index: Int ->
                Column (modifier = Modifier.clickable {
                    oneProductClicked(list[index])
                }) {
                    Row(
                        modifier = Modifier.fillMaxWidth().border(1.dp, Color.Black).padding(10.dp)
                    ) {
                        Column(modifier = Modifier.fillMaxWidth(0.5f)) {
                            Text(fontSize = 20.sp, text = "Product: " + list[index].name)
                            Text(
                                fontSize = 20.sp,
                                text = "Price: " + list[index].price.toString()
                            )
                        }
                        Column (modifier = Modifier.fillMaxWidth(0.4f)) {
                            Text(
                                fontSize = 20.sp,
                                text = "Quantity: " + list[index].quantity.toString()
                            )
                        }
                        Spacer(modifier = Modifier.fillMaxWidth(0.2f))
                        Column () {
                            Button(onClick = {
                                oneProductToDelete(list[index])
                            }) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete", modifier = Modifier.background(Color.Gray))
                            }
                        }
                    }
                    Spacer(Modifier.height(10.dp))
                }
            }
        }

    }
}
