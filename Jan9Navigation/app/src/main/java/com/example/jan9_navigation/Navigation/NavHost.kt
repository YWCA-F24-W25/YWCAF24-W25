package com.example.jan9_navigation.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jan9_navigation.Screens.MariaCalculatorScreen
import com.example.jan9_navigation.Screens.MyCalculatorScreen
import com.example.jan9_navigation.Screens.NeelamCalculatorScreen


@Composable
fun MyNavGraph(navController: NavHostController, ){

    NavHost(navController = navController,
        startDestination = NavItem.myCalculator.path) {

        composable(route = NavItem.myCalculator.path) { MyCalculatorScreen() }
        composable(route = NavItem.MariaCalculator.path) { MariaCalculatorScreen() }
        composable(route = NavItem.NeelamCalculator.path) { NeelamCalculatorScreen() }

    }
}