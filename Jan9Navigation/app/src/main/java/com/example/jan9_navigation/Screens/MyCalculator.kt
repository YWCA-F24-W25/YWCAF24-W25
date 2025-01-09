package com.example.jan9_navigation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.unit.dp

@Composable
fun MyCalculatorScreen(){
    var resultText = remember { mutableStateOf("") }
    var historyButtonCLicked = remember { mutableStateOf(false) }
    val historyList = remember { mutableStateListOf<String>() }

    var op = ""
    var firstOprand = ""
    var secondOprand = ""

    Column (modifier = Modifier.fillMaxSize().imePadding(),
        verticalArrangement = Arrangement.spacedBy(1.dp),
       // verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally) {
// 66 + 5
        Text(resultText.value)
        Column (modifier = Modifier.fillMaxHeight(0.7f).imePadding(),) {
            KeyPad(buttonClicked = { title ->
                if (title.equals("C")){
                resultText.value = ""
                }
                else if (title.equals("+")){
                    firstOprand =  resultText.value
                    resultText.value += title
                    op = "+"

                }
                else if (title.equals("-")){
                    firstOprand =  resultText.value
                    resultText.value += title
                    op = "-"
                }
                else if (title.equals("*")){
                    firstOprand =  resultText.value
                    resultText.value += title
                    op = "*"
                }
                else if (title.equals("/")){
                    firstOprand =  resultText.value
                    resultText.value += title
                    op = "/"
                }
                else if (title.equals("=")) {
                    if (historyButtonCLicked.value) {
                        historyList.add("55 + 2 = 57")// 55 + 9 = 3
                    }
                }
                else {
                    resultText.value += title
                }

            })
        }

        Button(onClick = {
            historyButtonCLicked.value = !historyButtonCLicked.value
        }) {
            Text("History")
        }
        if (historyButtonCLicked.value) {
            LazyColumn(
                modifier = Modifier.fillMaxHeight().padding(8.dp)
            ) {
                items(historyList.size) { index ->
                    val current = historyList[index]
                    Card(
                        modifier = Modifier.fillMaxHeight().fillMaxWidth()
                            .background(Color.LightGray)
                    ) {
                        Text(current)

                    }
                }
            }
        }

    }



}

@Composable
fun KeyPad(
    buttonClicked: (String)->Unit
){
    Row (modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
        ){
       OnButton(modifier = Modifier,"C") { buttonClicked("C") }
        OnButton(modifier = Modifier,"+/-") { buttonClicked("+/-") }
        OnButton(modifier = Modifier,"%") { buttonClicked("%") }
        OneOrangeButton(  modifier = Modifier,
            "/") { buttonClicked("/") }
    }
    Row (modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ){
        OnButton(modifier = Modifier,"7") { buttonClicked("7") }
        OnButton(modifier = Modifier,"8") { buttonClicked("8") }
        OnButton(modifier = Modifier,"9") { buttonClicked("9") }
        OneOrangeButton(modifier = Modifier,"*") { buttonClicked("*") }

    }
    Row (modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ){
        OnButton(modifier = Modifier,"4") { buttonClicked("4") }
        OnButton(modifier = Modifier,"5") { buttonClicked("5") }
        OnButton(modifier = Modifier,"6") { buttonClicked("6") }
        OneOrangeButton(modifier = Modifier,"-") { buttonClicked("-") }
    }
    Row (modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ){
        OnButton(modifier = Modifier,"1") { buttonClicked("1") }
        OnButton(modifier = Modifier,"2") { buttonClicked("2") }
        OnButton(modifier = Modifier,"3") { buttonClicked("3") }
        OneOrangeButton(modifier = Modifier,"+") { buttonClicked("+") }
    }
    Row (modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ){
        OnButton(modifier = Modifier.fillMaxWidth(0.5f),"0") { buttonClicked("0") }
        OnButton(modifier = Modifier,".") { buttonClicked(".") }
        OneOrangeButton(modifier = Modifier,"=") { buttonClicked("=") }
    }
   }

@Composable
fun OnButton( modifier: Modifier ,
              title: String,
              buttonClicked: (String) -> Unit,
            ){
    Button (
        modifier = modifier,
        onClick = {
        buttonClicked(title)
    }){
        Text(modifier = modifier, text = title)
    }
}

@Composable
fun OneOrangeButton( modifier: Modifier ,
              title: String,
              buttonClicked: (String) -> Unit,
){
    Button (
        colors = ButtonDefaults.buttonColors(Color.LightGray),
        modifier = modifier,
        onClick = {
            buttonClicked(title)
        }){
        Text(modifier = modifier, text = title)
    }
}