package com.example.carownerapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.benchmark.perfetto.ExperimentalPerfettoTraceProcessorApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.carownerapp.Room.Car
import com.example.carownerapp.Room.CarsOwnersDB
import com.example.carownerapp.Room.Owner
import com.example.carownerapp.ViewModel.AppRepository
import com.example.carownerapp.ViewModel.DBViewModel
import com.example.carownerapp.ViewModel.ViewModelFactory
import com.example.carownerapp.ui.theme.CarOwnerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var db = CarsOwnersDB.getDBInstance(this)
        var appRepo = AppRepository(db)
        var vmFactory = ViewModelFactory(appRepo)
        var vm = ViewModelProvider(this,vmFactory)[DBViewModel::class.java]

        enableEdgeToEdge()
        setContent {

            CarOwnerAppTheme {
                var showDialog by remember { mutableStateOf(false) }
                var showDeleteDialog by remember { mutableStateOf(false) }
                var toDeleteOwner by remember { mutableStateOf<Owner?>(null) }

                Scaffold(modifier = Modifier.fillMaxSize()
                , floatingActionButton = {
                        FloatingActionButton(onClick = {
                            showDialog = true
                        })
                        {
                            Icon(Icons.Default.Add, contentDescription = null)
                        }
                    })
                { innerPadding ->
                    Column {
                    MainUI(
                        vm,
                        modifier = Modifier.padding(innerPadding),
                        onDelete = {
                            toDeleteOwner = it
                            showDeleteDialog = true
                        }
                    )
                        if (showDeleteDialog){
                           var cars = toDeleteOwner?.let { vm.getAllCarsForOwner(it.id).collectAsState(initial = emptyList()).value }

                            DeleteAlertDialog(tilteText = "Are You Sure You Want to Delete This Owner",
                                onYes = {
                                    toDeleteOwner?.let {
                                        if (cars != null) {
                                            for (car in cars){
                                                vm.deleteOneCar(car)
                                            }
                                        }
                                        vm.deleteOneOwner(it)
                                    }
                                    showDeleteDialog = false
                                },
                                onNo = {
                                    showDeleteDialog = false
                                })
                        }
                    if (showDialog){
                        AddingAlertDialog(
                            firstText = "Enter Owner Name",
                            secondText = "Enter Year of Birth",
                            tilteText = "Adding New Owner",
                            onSave = {name, year ->
                            vm.addOneOwnerToDB(Owner(Math.random().toInt(), name, year.toInt()))
                                showDialog = false
                        }, onCancel = {
                                showDialog = false
                        })
                    }
                    }
                }
            }
        }
    }
}

@Composable
fun MainUI( vm : DBViewModel,
            modifier: Modifier = Modifier,
            onDelete: (Owner)->Unit) {
    var context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }
    var list = if (searchQuery.isEmpty()) {
        vm.getAllOwners().collectAsState(initial = emptyList()).value

    } else {
        vm.searchForOwner(searchQuery).collectAsState(initial = emptyList()).value
    }
    Column {
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search Owner") },
            modifier = Modifier.padding(16.dp)
        )
    LazyColumn(
    ) {
        items(list.size) { index ->
            OwnerItem(owner = list[index],
                ondelete = { owner ->
                    onDelete(owner)
                }, onClick = {oid ->
                    var i = Intent(context,CarsForOwner::class.java)
                    i.putExtra("oid",oid)
                    context.startActivity(i);
                })
        }
    }
}
}

@OptIn(ExperimentalPerfettoTraceProcessorApi::class)
@Composable
fun OwnerItem(owner: Owner, ondelete: (Owner)->Unit, onClick: (Int)->Unit) {
    var selectedIndex by remember {mutableStateOf(-1)}
    var context = LocalContext.current

    Row(modifier = Modifier.
            fillMaxWidth()
            .padding(16.dp).selectable(
                selected = owner.id == selectedIndex,
                onClick = {
                    if (selectedIndex != owner.id) {
                        selectedIndex = owner.id
                        onClick(owner.id)
                    }
                    else selectedIndex = -1
                }
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = owner.fullname)
        Text(text = owner.yob.toString())
        IconButton(onClick = {
            ondelete(owner)
        }) {
            Icon(Icons.Default.Delete, contentDescription = "Delete")
        }
    }
}





