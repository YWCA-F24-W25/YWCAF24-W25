package com.example.jan9_navigation.Scaffold

import android.widget.Toast
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.jan9_navigation.Navigation.NavItem


@Composable
fun MyBottomBar(onNavigate : (String)->Unit  ){
    val navItems = listOf(NavItem.myCalculator, NavItem.MariaCalculator, NavItem.NeelamCalculator)
    var context = LocalContext.current
    var selectedIndex = remember { mutableStateOf(0) }
    NavigationBar {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedIndex.value,
                label = { Text(text = item.title) },
                onClick = {
                    selectedIndex.value = index
                    Toast.makeText(context,item.title, Toast.LENGTH_SHORT).show()
                    onNavigate(item.path)
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.title)
                }
            )

        }
    }

}
