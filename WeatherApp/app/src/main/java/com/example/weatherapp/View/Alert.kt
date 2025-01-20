package com.example.weatherapp.View

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DBAlert(city: String, onSave: ()->Unit, onNotSave: ()->Unit ){

    BasicAlertDialog(onDismissRequest = {}) {
        Column {
            Text("Do You Want to Save $city into DB")
            Row {
                Button(onClick = {
                    onSave()
                }) {
                    Text("Yes")
                }
                Button(onClick = {
                    onNotSave()
                }) {
                    Text("No")
                }
            }
        }

    }
}