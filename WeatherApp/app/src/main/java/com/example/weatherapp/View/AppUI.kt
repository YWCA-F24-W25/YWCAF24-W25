package com.example.weatherapp.View

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarUI(searchForCity: (String)->Unit){

  var searchText by remember { mutableStateOf("") }
    var activeStateOfSearchBar by remember { mutableStateOf(true) }
        SearchBar(
            modifier = Modifier.fillMaxWidth().padding(5.dp),
            query = searchText,
            onQueryChange = {
                searchText = it
                // do the real job of search
                if (searchText.length > 2){
                    searchForCity(searchText)
                }else {
                    searchForCity("")
                }
            },
            onSearch = {
                activeStateOfSearchBar = true
            },
            active = activeStateOfSearchBar,
            onActiveChange = {
                activeStateOfSearchBar = it
            },
            placeholder = { Text("Search for cities with 3 chars") },
            trailingIcon = { Icons.Default.Search}
        ){}
    }


@Composable
fun CityTable(list: List<String>, onOneCitySelected : (String) -> Unit){

    Log.d("list",list.size.toString())
    LazyColumn {
        items(count = list.size)
        {
            index ->
            Row (modifier = Modifier.fillMaxWidth().padding(5.dp).clickable {
                onOneCitySelected(list[index])
            }) {
                Text(list[index], fontSize = 18.sp )
            }

        }
    }


}