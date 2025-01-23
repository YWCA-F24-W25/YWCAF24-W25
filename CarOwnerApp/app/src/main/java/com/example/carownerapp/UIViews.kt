package com.example.carownerapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddingAlertDialog(
    firstText: String,
    secondText: String,
    tilteText: String,
    onSave: (String, String) -> Unit,
    onCancel: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var yearOfBirth by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onCancel,
        title = {
            Text(text =tilteText)
        },
        text = {
            Column {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(firstText) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
                TextField(
                    value = yearOfBirth,
                    onValueChange = { yearOfBirth = it },
                    label = { Text(secondText) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                onSave(name, yearOfBirth)
            }) {
                Text("Save")
            }
        },

        dismissButton = {
            Button(onClick = onCancel) {
                Text("Cancel")
            }
        }
    )
}



@Composable
fun DeleteAlertDialog(
    tilteText: String,
    onYes: () -> Unit,
    onNo: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onNo,
        title = {
            Text(text =tilteText)
        },
        confirmButton = {
            Button(onClick = onYes) {
                Text("Yes")
            }
        },

        dismissButton = {
            Button(onClick = onNo) {
                Text("No")
            }
        }
    )
}
