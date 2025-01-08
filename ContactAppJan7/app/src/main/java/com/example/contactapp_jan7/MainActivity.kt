package com.example.contactapp_jan7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import com.example.contactapp_jan7.ui.theme.ContactAppJan7Theme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactAppJan7Theme {
                Scaffold(
                    topBar = {
                        TopAppBar(title = {
                            Text(text = "Contact App")
                        })
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    AppBody(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

}