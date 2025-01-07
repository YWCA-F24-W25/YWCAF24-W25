package com.example.jan7_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.magnifier
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.jan7_project.ui.theme.Jan7ProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            val windowMetrics = WindowMetricsCalculator.getOrCreate().
//            computeCurrentWindowMetrics(this)
//            val windowSizeClass = calculateWindowSizeClass(windowMetrics)
//            MyAdaptiveScreen(windowSizeClass)
            Jan7ProjectTheme {
                Scaffold (
                    bottomBar = {MyBottomBar()},
                    topBar = {MyTopBar()},
                    floatingActionButton = { MyFavButton() },
                    floatingActionButtonPosition = FabPosition.End
                    ) { innerPadding ->
                    APpBody(modifier =  Modifier.padding(innerPadding))
                }
            }

        }
    }
}

@Composable
fun APpBody(modifier: Modifier = Modifier){
    Text(text = "Hello")
}
//
//@Composable
//fun MyAdaptiveScreen(windowSizeClass: WindowSizeClass){
//    when (windowSizeClass){
//        WindowSizeClass.COMPACT -> CompactLayout()
//        WindowSizeClass.MEDIUM -> MediumLayout()
//        WindowSizeClass.EXPANDED -> ExpandedLayout()
//    }
//}
//
//@Composable
//fun CompactLayout(){}
//
//@Composable
//fun MediumLayout(){
//    // profile composable
//    // then go to list of contact
//}
//
//@Composable
//fun ExpandedLayout(){
//    //profile composable
//    // list of contact
//}