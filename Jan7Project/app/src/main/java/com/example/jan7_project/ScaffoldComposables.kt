package com.example.jan7_project

import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.week9_navigation.Navigation.NavItem


@Composable
fun MyBottomBar(){
    val navItems = listOf(NavItem.Search, NavItem.Call, NavItem.Email, NavItem.Favorite)
    var context = LocalContext.current
    var selectedIndex = remember { mutableStateOf(0) }
    NavigationBar {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedIndex.value,
                label = {Text(text = item.title)},
                onClick = {
                    selectedIndex.value = index
                    Toast.makeText(context,item.title,Toast.LENGTH_SHORT).show()
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.title)
                }
                )

        }
    }

}

@Composable
fun MyFavButton(){
    var context = LocalContext.current

    FloatingActionButton(onClick = {
        Toast.makeText(context,"Fab clicked",Toast.LENGTH_SHORT).show()

    } , content = {
        Icon(Icons.Default.Add, contentDescription = "add new")
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(){
    var context = LocalContext.current
    TopAppBar(
        title = { Text(text = "My App") },
        navigationIcon = {
            IconButton(onClick = {
                Toast.makeText(context,"Menu clicked",Toast.LENGTH_SHORT).show()
            }) {
                Icon(Icons.Default.Menu, contentDescription = "menu")
            }
        },
        actions = {
            IconButton(onClick = {}) { Icon(Icons.Default.Settings, contentDescription = "setting")}
            IconButton(onClick = {}) {Icon(Icons.Default.AddCircle, contentDescription = "add") }
        }
    )




}