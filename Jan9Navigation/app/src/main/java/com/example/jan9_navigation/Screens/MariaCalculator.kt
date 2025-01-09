package com.example.jan9_navigation.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import com.example.jan9_navigation.Navigation.NavItem

@Composable
fun MariaCalculatorScreen() {
       val historyList = remember { mutableStateListOf<String>() }
        val historyButtonCLicked = remember { mutableStateOf(false) }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            TopUI(addHistory = { history ->
                historyList.add(history)
            }, historyButtonCLicked.value)
            Button(onClick = {
                historyButtonCLicked.value = !historyButtonCLicked.value
            }) {
                if (!historyButtonCLicked.value)
                    Text("Advance - With History")
                else
                    Text("Standard - No History")
            }
            Spacer(modifier = Modifier.height(8.dp))
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
    fun TopUI(addHistory: (String) -> Unit, historyClicked: Boolean) {
        val display = remember { mutableStateOf("0") }
        val listOfButtons =
            listOf("1", "2", "3", "+", "4", "5", "6", "-", "7", "8", "9", "*", "C", "0", "=", "/")
        Column (modifier = Modifier.fillMaxHeight(0.8f)){
            Text(
                text = display.value,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(bottom = 8.dp),
            )
            listOfButtons.chunked(4).forEach { row ->
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    row.forEach { number ->
                        KeypadButton(number) {
                            if (number != "C" && number != "=") {
                                if (display.value == "0") {
                                    display.value = number
                                } else {
                                    if (number == "/" || number == "-" || number == "+" || number == "*") {
                                        display.value += " $number "
                                    } else {
                                        display.value += number
                                    }
                                }
                            } else {
                                if (number == "C") {
                                    display.value = ""
                                }
                                if (number == "=") {
                                    val result = calculateResult(display.value)
                                    if (historyClicked) {
                                        addHistory(display.value + " = $result")

                                    }
                                    display.value = result
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))

            }

        }
    }

    @Composable
    fun KeypadButton(label: String, onClick: () -> Unit) {
        Button(onClick = onClick) {
            Text(text = label)
        }
    }

    fun calculateResult(expression: String): String {
        if (expression.isEmpty()) return ""
        val tokens: List<String> =
            expression.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toList()
        val numbers: MutableList<Double> = mutableListOf()
        val operators: MutableList<Char> = mutableListOf()
        for (token in tokens) {
            if (isNumber(token)) {
                numbers.add(token.toDouble())
            } else if (isOperator(token[0])) {
                while (operators.isNotEmpty() && hasPrecedence(
                        token[0],
                        operators[operators.size - 1]
                    )
                ) {

                    val b = numbers.removeAt(numbers.size - 1)
                    val a = numbers.removeAt(numbers.size - 1)
                    val op = operators.removeAt(operators.size - 1)
                    numbers.add(performOperation(a, b, op))
                }
                operators.add(token[0])
            }
        }
        while (operators.isNotEmpty()) {
            val b = numbers.removeAt(numbers.size - 1)
            val a = numbers.removeAt(numbers.size - 1)
            val op = operators.removeAt(operators.size - 1)
            numbers.add(performOperation(a, b, op))
        }
        return numbers[0].toString()
    }

    fun isNumber(str: String): Boolean {
        return str.toDoubleOrNull() != null
    }

    fun isOperator(c: Char): Boolean {
        return c == '+' || c == '-' || c == '*' || c == '/'
    }

    fun performOperation(a: Double, b: Double, operator: Char): Double {
        return when (operator) {
            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            '/' -> a / b
            else -> throw IllegalArgumentException("Unsupported operator")
        }
    }

    fun hasPrecedence(op1: Char, op2: Char): Boolean {
        val precedence = mapOf(
            '+' to 1,
            '-' to 1,
            '*' to 2,
            '/' to 2,
        )
        return precedence[op1]!! <= precedence[op2]!!
    }
