package com.example.jan6_project2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jan6_project2.ui.theme.Jan6Project2Theme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var name = intent.getStringExtra("newName")
            val names = listOf("Mary","John","Lee")
            var newNames = names + name
            ListOfNamesComposable( newNames)
        }
    }
}

@Composable
fun ListOfNamesComposable(list: List<String?>){

    Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
        LazyColumn (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
            ){
            items(list){
                Row {
                    Image(painter = painterResource(R.drawable.user),
                        contentDescription = "user")
                    Spacer(modifier = Modifier.width(10.dp))
                    if (it != null) {
                        Text(text = it)
                    }
                }
            }
        }
    }
}







@Preview(showBackground = true)
@Composable
fun SecondPreview() {
    Jan6Project2Theme {
       // ListOfNamesComposable()
    }
}