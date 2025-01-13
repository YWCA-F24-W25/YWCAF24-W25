package com.example.productsapp.View

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import com.example.productsapp.Model.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppUI( list: SnapshotStateList<Product>, addNewProductClicked: ()->Unit){
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
        LazyColumn (modifier = Modifier.padding(innerPadding)) {
            // list
        }

    }
}
