package com.example.climbingapp.ui.session

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.climbingapp.model.climbs.Gym
import com.example.climbingapp.model.climbs.Location
import com.example.climbingapp.model.session.Session
import com.example.climbingapp.ui.theme.ClimbingAppTheme

/**
 * Current Session Screen
 */
@Composable
fun CurrentSessionScreen(addClimb: () -> Unit, session: Session, modifier: Modifier = Modifier) {
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
            SessionDisplay(session)
        }
        Button(onClick = addClimb) {
            Text(text = "Add Climb")
        }
    }
}

@Composable
@Preview
fun CurrentSessionPreview() {
    ClimbingAppTheme {
        CurrentSessionScreen({}, Session(Gym(Location("CHCH","NZ"), "YAC")) ,Modifier.background(Color.White))
    }
}