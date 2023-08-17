package com.example.climbingapp.ui.pastsession

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.climbingapp.init.ClimbInit
import com.example.climbingapp.ui.session.SessionDisplay

@Composable
fun PastSessionScreen(uiState: PastSessionUiState,returnToPastSessions: () -> Unit, modifier: Modifier = Modifier) {
    if(uiState.activeSession == null) {
        returnToPastSessions()
    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            uiState.convertToSessionUiState()?.let {
                SessionDisplay(sessionUiState = it, Modifier)
            }
            Row(
                Modifier.fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                ElevatedButton(onClick = returnToPastSessions) {
                    Text("Back")
                }
            }
        }
    }
}

@Preview
@Composable
fun PastSessionPreview() {
    val session = ClimbInit().setUpSession()
    val uiState = PastSessionUiState(
        activeSession = session
    )
    PastSessionScreen(uiState, {}, Modifier.background(Color.White))
}