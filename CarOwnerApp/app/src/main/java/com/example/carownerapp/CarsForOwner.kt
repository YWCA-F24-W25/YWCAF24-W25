package com.example.carownerapp

import android.content.Intent
import android.os.Bundle
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.carownerapp.Room.Car
import com.example.carownerapp.Room.Owner
import com.example.carownerapp.ViewModel.CarsViewModel
import com.example.carownerapp.ViewModel.CarsViewModelFactory
import com.example.carownerapp.ViewModel.DBViewModel
import com.example.carownerapp.ViewModel.ViewModelFactory
import com.example.carownerapp.ui.theme.CarOwnerAppTheme

class CarsForOwner : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var oid = intent.getIntExtra("oid",0)

        var vmFactory = CarsViewModelFactory(this)
        var cvm = ViewModelProvider(this,vmFactory)[CarsViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            var l = cvm.getOwnerName(oid).collectAsState(initial = emptyList()).value

            var showDialog by remember { mutableStateOf(false) }
            var showDeleteDialog by remember { mutableStateOf(false) }
            var toDeleteCar by remember { mutableStateOf<Car?>(null) }

            CarOwnerAppTheme {

                Scaffold(modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        FloatingActionButton(onClick = {
                            showDialog = true
                        })
                        {
                            Icon(Icons.Default.Add, contentDescription = null)
                        }
                    }
                    ) { innerPadding ->
                    Column {
                        if (l.size > 0) {
                            Text(
                                "${l[0].fullname}'s Cars",
                                fontWeight = FontWeight.Bold,
                                fontSize = 40.sp,
                            )
                        }
                    MainUI(
                        oid,
                        cvm,
                        modifier = Modifier.padding(innerPadding),
                        onDelete = {
                            toDeleteCar = it
                            showDeleteDialog = true
                        }
                    )
                        if (showDeleteDialog){
                            DeleteAlertDialog(tilteText = "Are You Sure You Want to Delete This Car?",
                                onYes = {
                                    toDeleteCar?.let { cvm.deleteOneCar(it) }
                                    showDeleteDialog = false
                                },
                                onNo = {
                                    showDeleteDialog = false
                                })
                        }
                        if (showDialog){
                            AddingAlertDialog(
                                firstText = "Enter Car Model",
                                secondText = "Enter Car Year",
                                tilteText = "Adding New Car",
                                onSave = {name, year ->
                                    cvm.addCarToOwner(Car(
                                        Math.random().toInt(),
                                        name,
                                        year.toInt(),
                                        oid
                                    ))
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
fun MainUI( oid: Int,
    vm : CarsViewModel,
            modifier: Modifier = Modifier,
            onDelete: (Car)->Unit) {
    var list = vm.getAllCarsForOwner(oid).collectAsState(initial = emptyList()).value

    Column {
        LazyColumn(
        ) {
            items(list.size) { index ->
                CarItem(list[index],
                    ondelete = { c ->
                        onDelete(c)
                    })
            }
        }
    }
}

@OptIn(ExperimentalPerfettoTraceProcessorApi::class)
@Composable
fun CarItem(car: Car, ondelete: (Car)->Unit) {
    var selectedIndex by remember { mutableStateOf(-1) }
    var context = LocalContext.current

    Row(modifier = Modifier.
    fillMaxWidth()
        .padding(16.dp).selectable(
            selected = car.id == selectedIndex,
            onClick = {
                if (selectedIndex != car.id) {
                    selectedIndex = car.id

                }
                else selectedIndex = -1
            }
        ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = car.model)
        Text(text = car.year.toString())
        IconButton(onClick = {
            ondelete(car)
        }) {
            Icon(Icons.Default.Delete, contentDescription = "Delete")
        }
    }
}





