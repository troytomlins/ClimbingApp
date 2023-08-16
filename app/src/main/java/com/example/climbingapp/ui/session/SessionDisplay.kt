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
import com.example.climbingapp.model.session.Session
import com.example.climbingapp.ui.theme.ClimbingAppTheme
import kotlin.math.roundToInt

/**
 * Display for sessions (current & past)
 */
@Composable
fun SessionDisplay(session: Session, modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Climbs Attempted: ${session.getClimbsInSession().size}")
        Text(
            text = "Climbs Completed: ${session.getNumClimbsCompleted()}"
        )
        Text(
            text = "Highest Grade: ${session.getHighestGradeInSession()}"
        )
        Text(text = "Average Grade: ${calculateAverageGrade(session)}")
        Text(text = "Last Climb: \n${session.getClimbsInSession().last().display()}")
    }
}

fun calculateAverageGrade(session: Session): Int {
    var count = 0.00
    var total = 0.00
    session.getClimbsInSession().forEach {
        if (it.sent) {
            count += 1
            total += it.route.grade
        }
    }
    return (total / count).roundToInt()
}

@Composable
@Preview
fun SessionPreview() {
    ClimbingAppTheme {
        SessionDisplay(
            ClimbInit().setUpSession(),
            Modifier.background(Color.White)
        )
    }
}