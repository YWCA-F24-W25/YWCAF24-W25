package com.example.contactapp_jan7

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext



@Composable
fun AppBody(
    modifier: Modifier = Modifier
) {
    var contacts = remember { mutableStateOf(listOf<Contact>()) }

    Column(modifier.fillMaxSize()) {
        TopUIPart(addNewContact = { name, number ->
            contacts.value = contacts.value + Contact(name, number)
        })
        LazyColumn(
            modifier = Modifier.fillMaxHeight().clickable {
                //   Toast.makeText(context, "Contact Selected ", Toast.LENGTH_LONG).show()
            }) {
            items(contacts.value.size) { index ->
                val currentContact = contacts.value[index]
                Card(
                    modifier = Modifier.fillMaxHeight().fillMaxWidth()
                        .background(Color.LightGray)
                ) {
                    Text(currentContact.name)
                    Text(currentContact.number)
                }
            }
        }

    }
}

@Composable
fun TopUIPart(addNewContact: (String, String) -> Unit) {
    var context = LocalContext.current
    var contactName = remember { mutableStateOf("") }
    var contactNumber = remember { mutableStateOf("") }//

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = contactName.value,
            onValueChange = {
                contactName.value = it
            },
            label = { Text("Contact Name") },
            placeholder = { Text("e.g. John ") }
        )
        TextField(
            value = contactNumber.value,
            onValueChange = {
                contactNumber.value = it
            },
            label = { Text("Contact Number") },
            placeholder = { Text("e.g. 647 444 3721 ") }
        )
        Button(onClick = {
            if (contactName.value.isEmpty() || contactNumber.value.isEmpty()) {
                Toast.makeText(context, "Missing Info!!", Toast.LENGTH_LONG).show()
            } else {
                // what will happend after adding a new contact
                addNewContact(contactName.value, contactNumber.value)
            }
        }) {
            Text("Add To List")
        }
    }
}