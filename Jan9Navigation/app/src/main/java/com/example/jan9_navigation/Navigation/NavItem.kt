package com.example.jan9_navigation.Navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings

sealed class NavItem {
    object myCalculator: Item("mycal","My Calculator", Icons.Default.Build)
    object MariaCalculator: Item("mary","Maria Calculator", Icons.Default.Settings)
    object NeelamCalculator: Item("neelam","Neelam Calculator", Icons.Default.CheckCircle)
}

