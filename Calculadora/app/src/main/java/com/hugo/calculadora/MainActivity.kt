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
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hugo.calculadora.ui.theme.CalculadoraTheme

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


@Composable
fun Calculate() {
    val number = remember { mutableFloatStateOf(0f) }
    val operator = remember { mutableStateOf<Char>(' ') }
    val result = remember { mutableFloatStateOf(0f) }

    when (operator.value) {
        '+' -> result.value = number.value + number.value
        '-' -> result.value = number.value - number.value
        '*' -> result.value = number.value * number.value
        '/' -> result.value = number.value / number.value
    }
}

fun updateNumber(number: String, newNumber: Char): String {
    if (number == "0" && newNumber == '0') return number
    if (number.contains('.') && newNumber == '.') return number
    if (newNumber == 'E'){
        if (number.length == 1) return "0"
        return number.dropLast(1)
    }
    if (newNumber == 'C') return "0"
    if (number == "0" && newNumber != '.') return newNumber.toString()
    return number + newNumber
}

@Composable
fun Calc(modifier: Modifier = Modifier) {
    var number = remember { mutableStateOf("0") }

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
                Button(onClick = {}, modifier = buttonModifier, colors = operatorBtnColors) { Text("√") }
                Button(onClick = {}, modifier = buttonModifier, colors = operatorBtnColors) { Text("%") }
                Button(onClick = {}, modifier = buttonModifier, colors = operatorBtnColors) { Text("+/-") }
                Button(onClick = {number.value = updateNumber(number.value,'E')}, modifier = buttonModifier, colors = clearBtnColors) { Text("CE") }
            }
            Row(modifier = Modifier.weight(0.5f)) {
                Button(onClick = {number.value = updateNumber(number.value,'7')}, modifier = buttonModifier, colors = numberBtnColors) { Text("7") }
                Button(onClick = {number.value = updateNumber(number.value,'8')}, modifier = buttonModifier, colors = numberBtnColors) { Text("8") }
                Button(onClick = {number.value = updateNumber(number.value,'9')}, modifier = buttonModifier, colors = numberBtnColors) { Text("9") }
                Button(onClick = {}, modifier = buttonModifier, colors = operatorBtnColors) { Text("/") }
            }
            Row(modifier = Modifier.weight(0.5f)) {
                Button(onClick = {number.value = updateNumber(number.value,'4')}, modifier = buttonModifier, colors = numberBtnColors) { Text("4") }
                Button(onClick = {number.value = updateNumber(number.value,'5')}, modifier = buttonModifier, colors = numberBtnColors) { Text("5") }
                Button(onClick = {number.value = updateNumber(number.value,'6')}, modifier = buttonModifier, colors = numberBtnColors) { Text("6") }
                Button(onClick = {}, modifier = buttonModifier, colors = operatorBtnColors) { Text("*") }
            }
            Row(modifier = Modifier.weight(0.5f)) {
                Button(onClick = {number.value = updateNumber(number.value,'1')}, modifier = buttonModifier, colors = numberBtnColors) { Text("1") }
                Button(onClick = {number.value = updateNumber(number.value,'2')}, modifier = buttonModifier, colors = numberBtnColors) { Text("2") }
                Button(onClick = {number.value = updateNumber(number.value,'3')}, modifier = buttonModifier, colors = numberBtnColors) { Text("3") }
                Button(onClick = {}, modifier = buttonModifier, colors = operatorBtnColors) { Text("-") }
            }
            Row(modifier = Modifier.weight(0.5f)) {
                Button(onClick = {number.value = updateNumber(number.value,'0')}, modifier = buttonModifier, colors = numberBtnColors) { Text("0") }
                Button(onClick = {number.value = updateNumber(number.value,'.')}, modifier = buttonModifier, colors = numberBtnColors) { Text(".") }
                Button(onClick = {}, modifier = buttonModifier, colors = numberBtnColors) { Text("=") }
                Button(onClick = {}, modifier = buttonModifier, colors = operatorBtnColors) { Text("+") }
            }
        }
    }
}

@Composable
fun Calculadora(modifier: Modifier = Modifier) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color(105,105,105))
    )
    {
        Row(
            Modifier
                .padding(50.dp)
        )
        {
            //MOSTRADOR
            Column(
                Modifier
                    .padding(25.dp)
                    .background(Color(51, 153, 102))
                    .fillMaxWidth()
            )
            {
                Text("NUMEROS PLACEHOLDER")
            }
        }
        Row(
            Modifier
                .padding(40.dp)
        )
        {
            //Linha 1
            Row(
                Modifier
                    .padding(5.dp)
            )
            {
                //MRC
                Column()
                {
                    Text("MRC")
                }
                //M-
                Column()
                {
                    Text("M-")
                }
                //M+
                Column()
                {
                    Text("M+")
                }
                //ON C
                Column()
                {
                    Text("ON/C")
                }
            }
            //Linha 2
            Row()
            {
                //RAIZ
                Column()
                {
                    Text("RAIZ")
                }
                //%
                Column()
                {
                    Text("%")
                }
                //+-
                Column()
                {
                    Text("+/-")
                }
                //CE
                Column()
                {
                    Text("CE")
                }
            }
            //Linha 3
            Row()
            {
                //7
                Column()
                {
                    Text("7")
                }
                //8
                Column()
                {
                    Text("8")
                }
                //9
                Column()
                {
                    Text("9")
                }
                // DIVIDIR
                Column()
                {
                    Text("/")
                }
            }
            //Linha 4
            Row()
            {
                //4
                Column()
                {
                    Text("4")
                }
                //5
                Column()
                {
                    Text("5")
                }
                //6
                Column()
                {
                    Text("6")
                }
                //*
                Column()
                {
                    Text("*")
                }
            }
            //Linha 5
            Row()
            {
                //1
                Column()
                {
                    Text("1")
                }
                //2
                Column()
                {
                    Text("2")
                }
                //3
                Column()
                {
                    Text("3")
                }
                //-
                Column()
                {
                    Text("-")
                }
            }
            //Linha 6
            Row()
            {
                //0
                Column()
                {
                    Text("0")
                }
                //.
                Column()
                {
                    Text(".")
                }
                //=
                Column()
                {
                    Text("=")
                }
                //+
                Column()
                {
                    Text("+")
                }
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