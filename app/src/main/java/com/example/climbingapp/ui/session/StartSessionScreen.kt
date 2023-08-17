package com.example.climbingapp.ui.session

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.climbingapp.ui.theme.ClimbingAppTheme

/**
 * Start new session screen
 */
@Composable
fun StartSessionScreen(onStart: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val gymString = "YMCA Adventure Centre \nChristchurch, New Zealand" // Temp
        Text(
            text = "Current Location:\n${gymString}",
            modifier = Modifier
        )
        Spacer(
            modifier = Modifier.height(64.dp)
        )
        Button(onClick = onStart) {
            Text(text = "Start New Session")
        }
    }
}

@Composable
@Preview
fun StartSessionPreview() {
    ClimbingAppTheme {
        StartSessionScreen({},Modifier.background(Color.White))
    }
}