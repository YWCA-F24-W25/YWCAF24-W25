package com.example.weatherapp.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertComposable(city: String,
                    message: String,
                    okButton: String,
                    noButton: String ,
                    onSave: ()->Unit,
                    onNotSave: ()->Unit ){
    AlertDialog(
        modifier = Modifier.background(Color.White),
        onDismissRequest = {}) {
        Column {
            Text(message)
            Row {
                Button(onClick = {
                    onSave()
                }) {
                    Text(okButton)
                }
                Button(onClick = {
                    onNotSave()
                }) {
                    Text(noButton)
                }
            }
        }

    }
}