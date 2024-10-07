package com.hugo.calculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                    Calculadora(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}



@Composable
fun Calculadora(modifier: Modifier = Modifier) {
    Box(
        Modifier
            .fillMaxSize()
    )
    {
        Column(
            Modifier
                .padding(50.dp)
                .background(Color(105,105,105))
        )
        {
            //MOSTRADOR
            Row(
                Modifier
                    .padding(20.dp)
                    .background(Color(51, 153, 102))
                    .fillMaxWidth()
            )
            {
                Text("NUMEROS PLACEHOLDER")
            }
        }
        Column(
            Modifier
                .padding(30.dp)
        )
        {
            //Linha 1
            Row()
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
fun GreetingPreview() {
    CalculadoraTheme {
        Calculadora()
    }
}