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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.contactapp_jan7.ui.theme.ContactAppJan7Theme

class MainActivity : ComponentActivity() {

    var contacts =  mutableListOf<Contact>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // contacts.add(Contact("test","test"))
        enableEdgeToEdge()
        setContent {
            ContactAppJan7Theme {
                Scaffold(
                    topBar = {
                        TopAppBar(title = {
                            Text(text = "Contact App")
                        })
                    } ,
                    modifier = Modifier.fillMaxSize()) {
                    innerPadding ->
                    AppBody(contacts,
                        modifier = Modifier.padding(innerPadding),
                        {
                            c ->
                            contacts.add(c)
                        }
                    )
                }
            }
        }
    }
}



