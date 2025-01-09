package com.example.jan9_navigation
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.jan9_navigation.Navigation.MyNavGraph
import com.example.jan9_navigation.Scaffold.MyBottomBar
import com.example.jan9_navigation.Scaffold.MyFavButton
import com.example.jan9_navigation.Scaffold.MyTopBar
import com.example.jan9_navigation.ui.theme.Jan9NavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Jan9NavigationTheme {
                val navController = rememberNavController()
                Scaffold (
                    bottomBar = { MyBottomBar(onNavigate = {path ->
                        navController.navigate(path)
                    })
                    },
                    topBar = { MyTopBar(onSettingClicked = {
                        var intent = Intent(this,SecondActivity::class.java)
                        startActivity(intent)
                    }) },
                    floatingActionButton = { MyFavButton() },
                    floatingActionButtonPosition = FabPosition.End
                    ) { innerPadding ->
                        Column(
                            modifier = Modifier.padding(innerPadding).fillMaxSize()
                        ) {
                            MyNavGraph(navController)

                        }
                }
            }

        }
    }
}

