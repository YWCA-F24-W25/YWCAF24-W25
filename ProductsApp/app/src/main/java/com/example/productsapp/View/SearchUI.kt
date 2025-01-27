package com.example.productsapp.View

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarUI(hint: String ,searchForCity: (String)->Unit){

    var searchText by remember { mutableStateOf("") }
    var activeStateOfSearchBar by remember { mutableStateOf(true) }
    SearchBar(
        modifier = Modifier.padding(5.dp).fillMaxHeight(0.15f),
        query = searchText,
        onQueryChange = {
            searchText = it
            // do the real job of search
            searchForCity(searchText)

        },
        onSearch = {
            activeStateOfSearchBar = true
        },
        active = activeStateOfSearchBar,
        onActiveChange = {
            activeStateOfSearchBar = it
        },
        placeholder = { Text("$hint") }
    ){}
}