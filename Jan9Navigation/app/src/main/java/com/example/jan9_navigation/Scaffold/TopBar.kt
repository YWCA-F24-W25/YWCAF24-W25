package com.example.jan9_navigation.Scaffold

import android.content.Intent
import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.jan9_navigation.SecondActivity


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar( onSettingClicked: ()->Unit){
    var context = LocalContext.current
    TopAppBar(
        title = { Text(text = "My App") },
        navigationIcon = {
            IconButton(onClick = {
                Toast.makeText(context,"Menu clicked", Toast.LENGTH_SHORT).show()
            }) {
                Icon(Icons.Default.Menu, contentDescription = "menu")
            }
        },
        actions = {
            IconButton(onClick = {
                onSettingClicked()


            })
            { Icon(Icons.Default.Settings, contentDescription = "setting") }
            IconButton(onClick = {}) { Icon(Icons.Default.AddCircle, contentDescription = "add") }
        }
    )
}