package com.example.climbingapp.ui.session

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.climbingapp.ui.theme.ClimbingAppTheme
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
        val route = Route(grade = Random.nextInt(16,19), color = "Yellow")
        val climb = Climb(route = route)
        SessionDisplay(
            SessionUiState(
                attempted = Random.nextInt(10, 12),
                completed = Random.nextInt(8,10),
                highestGrade = Random.nextInt(19, 25),
                averageGrade = Random.nextInt(16, 19),
                lastClimb = climb
            ),
            Modifier.background(Color.White)
        )
    }
}