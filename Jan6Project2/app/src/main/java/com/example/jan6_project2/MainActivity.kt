package com.example.jan6_project2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Label
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jan6_project2.ui.theme.Jan6Project2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column (modifier = Modifier.fillMaxSize()) {
                CounterComposable()
                LogInComposable()
            }
        }
    }
}


@Composable
fun CounterComposable(){
    var counter = remember { mutableStateOf(0) }// 1- state variable = 0
    Column(modifier = Modifier.
    fillMaxHeight(0.5f).
    fillMaxWidth(1f)
        .background(Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
     {
        // 3- state var is used here
        Text(text = "${counter.value}", fontSize = 30.sp)
        Row(){
            Button(colors = ButtonDefaults.buttonColors(Color.Red),
                onClick = {
                    counter.value++ //2- state var updated - reinvoke the function
                    Log.d("counter", "$counter")
            }) {
                Text(text = "++",fontSize = 30.sp)
            }
            Button(colors = ButtonDefaults.buttonColors(Color.Red),
                onClick = {
                    counter.value-- //2- state var updated
                    Log.d("counter", "$counter")
            }) {
                Text(text = "--", fontSize = 30.sp)
            }
        }
    }
}

@Composable
fun LogInComposable() {
    var userInput = remember { mutableStateOf("") }// 1- state variable = 0
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxHeight().
        fillMaxWidth(1f)
            .background(Color.Yellow),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {

        Image(painter = painterResource(R.drawable.img),
            contentDescription = "Login", modifier = Modifier.size(100.dp))
        TextField(
            placeholder = {
                Text(text = "Enter Your Name")
            },
            label = {
                Text(text = "Log In Here!!")
            },
            value = userInput.value,
            onValueChange = {
                userInput.value = it
            }
        )

       FloatingActionButton(onClick = {

           // to navigate between activities, we have to use intents
           // to navigate between composable, we have to use NavController
           if (!userInput.value.isEmpty()) {
               var intent = Intent(context, SecondActivity::class.java)
               intent.putExtra("newName", userInput.value)
               context.startActivity(intent)
           }
       })
       {
           Text(text = "Log In")
       }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Jan6Project2Theme {
        LogInComposable()
    }
}