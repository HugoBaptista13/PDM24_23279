package com.hugo.calculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hugo.calculadora.ui.theme.CalculadoraTheme
import kotlin.math.sqrt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Calc(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

var operator : Char = ' '
var number = mutableStateOf("0")
var buffer : String  = ""


fun calculate(firstNumber: String, secondNumber: String): String {
    val result : Double
    if (buffer.isBlank()) return number.value

    try { firstNumber.toDouble() } catch (e: NumberFormatException) { return e.message.toString() }
    try { secondNumber.toDouble() } catch (e: NumberFormatException) { return e.message.toString() }

    val number1 = firstNumber.toDouble()
    val number2 = secondNumber.toDouble()

    when (operator) {
        '+' -> {
            updateOperator(' ')
            buffer = ""
            result = number1 + number2
            if (result % 1 == 0.0) return result.toInt().toString()
            return result.toString()
        }
        '-' -> {
            updateOperator(' ')
            buffer = ""
            result = number1 - number2
            if (result % 1 == 0.0) return result.toInt().toString()
            return result.toString()
        }
        '*' -> {
            updateOperator(' ')
            buffer = ""
            result = number1 * number2
            if (result % 1 == 0.0) return result.toInt().toString()
            return result.toString()
        }
        '/' -> {
            updateOperator(' ')
            buffer = ""
            if (number2 == 0.0) return "MATH ERROR"
            result = number1 / number2
            if (result % 1 == 0.0) return result.toInt().toString()
            return result.toString()
        }
        else -> {
            buffer = ""
            return number2.toString()
        }
    }
}

fun sqrtNumber(number: String): String {
    if (number == "0") return number
    if (number.startsWith('-')) return "MATH ERROR"
    try { number.toDouble() } catch (e: NumberFormatException) { return e.message.toString() }

    val result : Double = sqrt(number.toDouble())
    if (result % 1 == 0.0) return result.toInt().toString()
    return result.toString()
}

fun negateNumber(number: String): String {
    if (number == "0") return number
    if (number.startsWith('-')) return number.drop(1)
    return "-$number"
}

fun updateOperator(newOperator: Char) {
    if (!(newOperator.isWhitespace()) && !(operator.isWhitespace()) && buffer.isNotBlank()) {
        // Calculate
        number.value = calculate(buffer, number.value)
    }
    operator = newOperator
    buffer = number.value
    number.value = "0"
}

fun updateNumber(number: String, newNumber: Char): String {
    if (number == "0" && newNumber == '0') return number
    if (number.contains('.') && newNumber == '.') return number
    if (newNumber == 'E'){
        if (number.length == 1) return "0"
        return number.dropLast(1)
    }
    if (newNumber == 'C') {
        updateOperator(' ')
        return "0"
    }
    if (number == "0" && newNumber != '.') return newNumber.toString()
    return number + newNumber
}

@Composable
fun Calc(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(105, 105, 105))
            .padding(20.dp)
    ) {
        // Display
        Box(
            modifier = Modifier
                .weight(0.4f)
                .fillMaxWidth()
                .background(Color(51, 153, 102))
                .padding(12.dp, 40.dp)
        ) {
            Text(
                text = number.value,
                textAlign = TextAlign.Right,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )
        }

        // Buttons
        val buttonModifier = Modifier
            .weight(0.5f)
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {  }

        val operatorBtnColors = ButtonDefaults.buttonColors((Color(10, 10, 10)))
        val clearBtnColors = ButtonDefaults.buttonColors((Color(250, 0, 70)))
        val numberBtnColors = ButtonDefaults.buttonColors((Color(150, 150, 150)))

        Column(modifier = Modifier.weight(1f)) {
            Row(modifier = Modifier.weight(0.5f)) {
                Button(onClick = {}, modifier = buttonModifier, colors = operatorBtnColors) { Text("MRC") }
                Button(onClick = {}, modifier = buttonModifier, colors = operatorBtnColors) { Text("M-") }
                Button(onClick = {}, modifier = buttonModifier, colors = operatorBtnColors) { Text("M+") }
                Button(onClick = {number.value = updateNumber(number.value,'C')}, modifier = buttonModifier, colors = clearBtnColors) { Text("ON/C") }
            }
            Row(modifier = Modifier.weight(1f)) {
                Button(onClick = {number.value = sqrtNumber(number.value) }, modifier = buttonModifier, colors = operatorBtnColors) { Text("√") }
                Button(onClick = {}, modifier = buttonModifier, colors = operatorBtnColors) { Text("%") }
                Button(onClick = {number.value = negateNumber(number.value)}, modifier = buttonModifier, colors = operatorBtnColors) { Text("+/-") }
                Button(onClick = {number.value = updateNumber(number.value,'E')}, modifier = buttonModifier, colors = clearBtnColors) { Text("CE") }
            }
            Row(modifier = Modifier.weight(0.5f)) {
                Button(onClick = {number.value = updateNumber(number.value,'7')}, modifier = buttonModifier, colors = numberBtnColors) { Text("7") }
                Button(onClick = {number.value = updateNumber(number.value,'8')}, modifier = buttonModifier, colors = numberBtnColors) { Text("8") }
                Button(onClick = {number.value = updateNumber(number.value,'9')}, modifier = buttonModifier, colors = numberBtnColors) { Text("9") }
                Button(onClick = {updateOperator('/')}, modifier = buttonModifier, colors = operatorBtnColors) { Text("/") }
            }
            Row(modifier = Modifier.weight(0.5f)) {
                Button(onClick = {number.value = updateNumber(number.value,'4')}, modifier = buttonModifier, colors = numberBtnColors) { Text("4") }
                Button(onClick = {number.value = updateNumber(number.value,'5')}, modifier = buttonModifier, colors = numberBtnColors) { Text("5") }
                Button(onClick = {number.value = updateNumber(number.value,'6')}, modifier = buttonModifier, colors = numberBtnColors) { Text("6") }
                Button(onClick = {updateOperator('*')}, modifier = buttonModifier, colors = operatorBtnColors) { Text("*") }
            }
            Row(modifier = Modifier.weight(0.5f)) {
                Button(onClick = {number.value = updateNumber(number.value,'1')}, modifier = buttonModifier, colors = numberBtnColors) { Text("1") }
                Button(onClick = {number.value = updateNumber(number.value,'2')}, modifier = buttonModifier, colors = numberBtnColors) { Text("2") }
                Button(onClick = {number.value = updateNumber(number.value,'3')}, modifier = buttonModifier, colors = numberBtnColors) { Text("3") }
                Button(onClick = {updateOperator('-')}, modifier = buttonModifier, colors = operatorBtnColors) { Text("-") }
            }
            Row(modifier = Modifier.weight(0.5f)) {
                Button(onClick = {number.value = updateNumber(number.value,'0')}, modifier = buttonModifier, colors = numberBtnColors) { Text("0") }
                Button(onClick = {number.value = updateNumber(number.value,'.')}, modifier = buttonModifier, colors = numberBtnColors) { Text(".") }
                Button(onClick = {number.value = calculate(buffer, number.value)}, modifier = buttonModifier, colors = numberBtnColors) { Text("=") }
                Button(onClick = {updateOperator('+')}, modifier = buttonModifier, colors = operatorBtnColors) { Text("+") }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculadoraPreview() {
    CalculadoraTheme {
        Calc()
    }
}