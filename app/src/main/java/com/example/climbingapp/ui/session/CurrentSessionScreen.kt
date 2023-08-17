package com.example.climbingapp.ui.session

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.climbingapp.init.ClimbInit
import com.example.climbingapp.ui.theme.ClimbingAppTheme
import com.example.climbingapp.viewmodel.SessionViewModel

/**
 * Current Session Screen
 */
@Composable
fun CurrentSessionScreen(
    addClimb: () -> Unit,
    sessionUiState : SessionUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier
                .fillMaxWidth()
                .requiredHeight(400.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            SessionDisplay(sessionUiState)
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "View completed climbs")
        }
        Spacer(Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = addClimb) {
                Text(text = "Add Climb")
            }
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
@Preview
fun CurrentSessionPreview() {
    ClimbingAppTheme {
        val session = ClimbInit().setUpSession()
        val sessionViewModel = SessionViewModel()
        sessionViewModel.setUpSession(session)
        CurrentSessionScreen({}, sessionViewModel.uiState.value, Modifier.background(MaterialTheme.colorScheme.background))
    }
}