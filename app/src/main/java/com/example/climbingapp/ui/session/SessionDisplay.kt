package com.example.climbingapp.ui.session

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.climbingapp.init.ClimbInit
import com.example.climbingapp.model.climbs.Gym
import com.example.climbingapp.model.climbs.Location
import com.example.climbingapp.model.session.Session
import com.example.climbingapp.ui.theme.ClimbingAppTheme
import com.example.climbingapp.viewmodel.SessionViewModel
import kotlin.math.roundToInt
import kotlin.random.Random

/**
 * Display for sessions (current & past)
 */
@Composable
fun SessionDisplay(sessionUiState: SessionUiState, modifier: Modifier = Modifier) {
    Column(
        modifier,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Climbs Attempted: ${sessionUiState.attempted}")
        Text(
            text = "Climbs Completed: ${sessionUiState.completed}"
        )
        Text(
            text = "Highest Grade: ${sessionUiState.highestGrade}"
        )
        Text(text = "Average Grade: ${sessionUiState.averageGrade}")
    }
}

@Composable
@Preview
fun SessionPreview() {
    ClimbingAppTheme {
        SessionDisplay(
            SessionUiState(
                attempted = Random.nextInt(10, 12),
                completed = Random.nextInt(8,10),
                highestGrade = Random.nextInt(19, 25),
                averageGrade = Random.nextInt(16, 19)
            ),
            Modifier.background(Color.White)
        )
    }
}