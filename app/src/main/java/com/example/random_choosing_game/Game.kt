package com.example.random_choosing_game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Game() {
    var choose by remember { mutableStateOf<String?>(null) } // Nullable String for user input
    var ch by remember { mutableStateOf<Int?>(null) }        // Nullable Int for the chosen value

    // Rainbow gradient background
    val rainbowBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xFFFF0000), // Red
            Color(0xFFFF7F00), // Orange
            Color(0xFFFFFF00), // Yellow
            Color(0xFF00FF00), // Green
            Color(0xFF0000FF), // Blue
            Color(0xFF4B0082), // Indigo
            Color(0xFF8B00FF)  // Violet
        )
    )

    // Single column with rainbow gradient background
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(rainbowBrush) // Apply rainbow gradient as the background
            .padding(16.dp), // Padding around the content
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TextField to accept a number as input
        TextField(
            value = choose ?: "", // Show an empty string if `choose` is null
            onValueChange = { input ->
                choose = input.ifBlank { null } // Set null if input is blank
            },
            label = { Text("Enter a number", color = Color.White) }, // White label text
            modifier = Modifier.padding(horizontal = 16.dp),
            singleLine = true
        )

        Button(
            onClick = {
                // Randomly choose a number between 1 and the entered value
                choose?.toIntOrNull()?.let { num ->
                    if (num > 0) {
                        ch = (1..num).random()
                    } else {
                        ch = null
                    }
                }
            },
            modifier = Modifier.padding(top = 16.dp),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF5722), // Orange color for the button
                contentColor = Color.White
            )
        ) {
            Text(text = "Choose Randomly", fontSize = 18.sp)
        }

        // Display the chosen value or placeholder text
        Text(
            text = ch?.let { "Chosen Value: $it" } ?: "No value chosen",
            modifier = Modifier.padding(top = 16.dp),
            color = Color.White, // White color for the text
            fontSize = 20.sp
        )
    }
}
