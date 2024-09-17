package com.example.assignment2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.assignment2.ui.theme.Assignment2Theme
import androidx.compose.ui.Alignment


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CelsiusToFahrenheitConverter(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CelsiusToFahrenheitConverter(modifier: Modifier = Modifier) {


    var celsius by remember { mutableStateOf(0f) }
    var fahrenheit by remember { mutableStateOf(32f) }

    fun celsiusToFahrenheit(celsius: Float): Float {
        return (celsius * 9 / 5) + 32
    }

    fun fahrenheitToCelsius(fahrenheit: Float): Float {
        return (fahrenheit - 32) * 5 / 9
    }

    val message = if (celsius <= 20f) "I wish it were warmer." else "I wish it were colder."

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "C-F Converter", style = MaterialTheme.typography.headlineLarge)
        Text(text = "Celsius: ${celsius.toInt()}°C", style = MaterialTheme.typography.titleMedium)
        Slider(
            value = celsius,
            onValueChange = {
                celsius = it
                fahrenheit = celsiusToFahrenheit(it)
            },
            valueRange = 0f..100f,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Text(text = "Fahrenheit: ${fahrenheit.toInt()}°F", style = MaterialTheme.typography.titleMedium)
        Slider(
            value = fahrenheit,
            onValueChange = {
                fahrenheit = if (it < 32f) 32f else it
                celsius = fahrenheitToCelsius(fahrenheit)
            },
            valueRange = 0f..212f,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = message, style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun CelsiusToFahrenheitConverterPreview() {
    Assignment2Theme {
        CelsiusToFahrenheitConverter()
    }
}
