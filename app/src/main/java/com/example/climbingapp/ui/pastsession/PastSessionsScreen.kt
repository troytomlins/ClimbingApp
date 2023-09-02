package com.example.climbingapp.ui.pastsession

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.climbingapp.R
import com.example.climbingapp.ScreenTitle
import com.example.climbingapp.init.ClimbInit
import com.example.climbingapp.model.session.Session
import com.example.climbingapp.ui.theme.ClimbingAppTheme
import com.example.climbingapp.viewmodel.PastSessionUiStatus

/**
 * Display for showing past sessions as scrolling list
 */
@Composable
fun PastSessionsScreen(
    uiState: PastSessionUiState,
    uiStatus: PastSessionUiStatus,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.fillMaxSize()
    ) {
        when (uiStatus) {
            is PastSessionUiStatus.Loading -> null // TODO: Add loading screen
            is PastSessionUiStatus.Success ->
                PastSessionsScreenContent(
                    uiState,
                    modifier
                )
            is PastSessionUiStatus.Error -> null // TODO: Add error screen
        }
    }
}

@Composable
fun PastSessionsScreenContent(
    uiState: PastSessionUiState,
    modifier: Modifier = Modifier
) {
    ScreenTitle(R.string.past_sessions, Modifier.fillMaxHeight(.2f))

    Row(
        modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(0.dp, 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .background(
                        MaterialTheme.colorScheme.secondaryContainer,
                        MaterialTheme.shapes.medium
                    )
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.secondary,
                        MaterialTheme.shapes.medium
                    )
            ) {
                if (uiState.sessions.size == 0) {
                    Text(
                        text = "No Past Sessions :(",
                        style = MaterialTheme.typography.headlineSmall
                    )
                } else {
                    LazyColumn() {
                        items(uiState.sessions.size) {
                            PastSessionCard(session = uiState.sessions[it], Modifier)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PastSessionCard(session: Session, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(4.dp, 2.dp)
            .fillMaxHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "${session.getSessionDateString()}-${session.gym.gymCode}")
            Text(text = "Climbs: ${session.getClimbsInSession().size}\nHighest: ${session.getHighestGradeInSession()}")
            OutlinedButton(
                onClick = {},
            ) {
                Text(
                    text = "View"
                )
            }
        }
    }
}

@Composable
@Preview
fun PastSessionsPreview() {
    ClimbingAppTheme {
        val sessions: MutableList<Session> = mutableListOf()
        sessions.add(ClimbInit().setUpSession())
        sessions.add(ClimbInit().setUpSession())
        sessions.add(ClimbInit().setUpSession())

        val sessionUiState = PastSessionUiState(
            sessions = sessions
        )
        PastSessionsScreen(sessionUiState, PastSessionUiStatus.Success(sessions.get(0)), Modifier.background(MaterialTheme.colorScheme.background))
    }
}