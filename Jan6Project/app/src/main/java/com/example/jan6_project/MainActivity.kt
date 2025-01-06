package com.example.jan6_project

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jan6_project.ui.theme.Jan6ProjectTheme
import java.time.Month

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExampleLayout()
                }
    }
}
@Composable
fun ExampleLayout(){
    Column(modifier =
    Modifier.fillMaxSize()
        .background(color = Color.LightGray).
        padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        )
    {
        SimpleRow()
        Spacer(modifier = Modifier.height(30.dp))
        SimpleColumn()
        Spacer(modifier = Modifier.height(30.dp))
        SimpleBox()
        Spacer(modifier = Modifier.height(30.dp))
        SimpleLazyColumn()
    }
}

// Row
@Composable
fun SimpleRow(){
    Row(modifier = Modifier.fillMaxWidth().
    padding(16.dp).background(color = Color.Green),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
        ) {
        Text(text = "Android", fontSize = 20.sp)
        Text(text = "Kotlin", fontSize  = 20.sp)
        Text(text = "Compose", fontSize = 20.sp)
    }
}

@Composable
fun SimpleColumn(){
    Column (modifier = Modifier.fillMaxWidth().
    padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Android", fontSize = 20.sp)
        Text(text = "Kotlin", fontSize  = 20.sp)
        Text(text = "Compose", fontSize = 20.sp)
    }
}

@Composable
fun SimpleBox(){
    Box(modifier = Modifier.size(100.dp).padding(6.dp),
        contentAlignment = Alignment.Center) {
        Icon(painter = painterResource(R.drawable.img),
            contentDescription = "notification",
            modifier = Modifier.size(80.dp),
            tint = Color.Cyan
        )
        Text(text = "9",
            color = Color.Black,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
            )
    }
}

@Composable
fun SimpleLazyColumn(){

    LazyColumn(modifier = Modifier.fillMaxWidth().
    background(Color.LightGray).padding(8.dp))
    {
        items(20){ index ->
            Text("Item Number $index ",
                modifier = Modifier.fillMaxWidth().padding(8.dp).clickable {
                    Log.d("myapp","clicked item is $index")
                })
        }
    }
}





@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Jan6ProjectTheme {
        ExampleLayout()
    }
}