package com.example.jan9_navigation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import com.example.jan9_navigation.Logic.Calculate

@Composable
    fun NeelamCalculatorScreen (modifier:Modifier=Modifier ) {
        var expression = remember{ mutableStateOf("")}
        var textValue = remember{ mutableStateOf("") }
        var historyList = remember { mutableStateOf(arrayListOf<String>()) }
        var context = LocalContext.current
        Column(modifier =Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(textValue.value,modifier = Modifier.fillMaxWidth())
            Column(modifier= Modifier.fillMaxWidth()) {
                Row (modifier=Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
                    Button(onClick = {
                        expression.value += "1"
                        textValue.value = expression.value
                    }) {
                        Text("1")
                    }
                    Button(onClick = {
                        expression.value += "2"
                        textValue.value = expression.value
                    }) {
                        Text("2")
                    }
                    Button(onClick = {
                        expression.value += "3"
                        textValue.value = expression.value
                    }) {
                        Text("3")
                    }
                    Button(onClick = {
                        expression.value += "+"
                        textValue.value = expression.value
                    }) {
                        Text("+")
                    }
                }
                Row (modifier=Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center){
                    Button(onClick = {
                        expression.value += "4"
                        textValue.value = expression.value
                    }) {
                        Text("4")
                    }
                    Button(onClick = {
                        expression.value += "5"
                        textValue.value = expression.value
                    }) {
                        Text("5")
                    }
                    Button(onClick = {
                        expression.value += "6"
                        textValue.value = expression.value
                    }) {
                        Text("6")
                    }
                    Button(onClick = {
                        expression.value += "-"
                        textValue.value = expression.value
                    }) {
                        Text("-")
                    }
                }
                Row(modifier=Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
                    Button(onClick = {
                        expression.value += "7"
                        textValue.value = expression.value
                    }) {
                        Text("7")
                    }
                    Button(onClick = {
                        expression.value += "8"
                        textValue.value = expression.value
                    }) {
                        Text("8")
                    }
                    Button(onClick = {
                        expression.value += "9"
                        textValue.value = expression.value
                    }) {
                        Text("9")
                    }
                    Button(onClick = {
                        expression.value += "*"
                        textValue.value = expression.value
                    }) {
                        Text("*")
                    }
                }
                Row (modifier=Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                    Button(onClick = {
                        expression.value += "0"
                        textValue.value = expression.value
                    }) {
                        Text("0")
                    }
                    Button(onClick = {
                        expression.value = ""
                        textValue.value = expression.value
                    }) {
                        Text("C")
                    }
                    Button(onClick = {
                        expression.value = expression.value.substring(0,(expression.value.length-1))
                    }) {
                        Text("B")
                    }
                    Button(onClick = {
                        expression.value += "/"
                        textValue.value = expression.value
                    }) {
                        Text("/")
                    }
                }
                Row (modifier=Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
                    Button(onClick = {
                        var result = Calculate(expression.value, context ).toString()
                        historyList.value.add(expression.value + "=" + result )
                        textValue.value = result
                        expression.value = ""
                    }) {
                        Text("=")
                    }

                }
            }
            Button(onClick = {
//                var intent = Intent(context, History::class.java)
//                intent.putStringArrayListExtra("historyList",historyList.value)
//                context.startActivity(intent)
            }) {
                Text("Click For History")
            }
        }

    }