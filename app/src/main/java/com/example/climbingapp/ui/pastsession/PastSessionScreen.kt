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
import com.example.climbingapp.ui.session.ActiveSession
import com.example.climbingapp.ui.session.Climb
import com.example.climbingapp.ui.session.Route
import com.example.climbingapp.ui.session.SessionDisplay
import com.example.climbingapp.ui.session.SessionUiState
import com.example.climbingapp.viewmodel.PastSessionUiStatus
import kotlin.random.Random

@Composable
fun PastSessionScreen(
    uiState: SessionUiState,
    returnToPastSessions: () -> Unit,
    modifier: Modifier = Modifier
) {
    if(uiState.attempted == 0) {
        returnToPastSessions()
    } else {
        PastSessionScreenContent(
            uiState = uiState,
            returnToPastSessions = returnToPastSessions,
            modifier = modifier
        )
    }
}
@Composable
fun PastSessionScreenContent(
    uiState: SessionUiState,
    returnToPastSessions: () -> Unit,
    modifier: Modifier = Modifier
) {
    if(uiState == null) {
        returnToPastSessions()
    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            SessionDisplay(sessionUiState = uiState, modifier)
            Row(
                Modifier
                    .fillMaxWidth()
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
    val colors = mutableListOf<String>()
    colors.add("Yellow")
    colors.add("Black")
    colors.add("Blue")
    colors.add("Red")
    colors.add("Orange")

    val session = ActiveSession()
    var i = 0
    while (i < Random.nextInt(3,15) ) {
        session.addClimb(
            Climb(
                route = Route(
                    Random.nextInt(),
                    "Route Name",
                    Random.nextInt(),
                    color = colors[Random.nextInt(0, colors.size)]
                )
            )
        )
        i += 1
    }
    val uiState = SessionUiState(
        session.getClimbs().size
    )
    PastSessionScreen(uiState, {}, Modifier.background(Color.White))
}

