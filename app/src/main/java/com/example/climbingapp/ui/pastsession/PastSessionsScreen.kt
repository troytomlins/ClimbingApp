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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.climbingapp.R
import com.example.climbingapp.init.ClimbInit
import com.example.climbingapp.model.session.Session
import com.example.climbingapp.ui.theme.ClimbingAppTheme

/**
 * Display for showing past sessions as scrolling list
 */
@Composable
fun PastSessionsScreen(sessionUiState: PastSessionUiState, modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxSize()
    ) {
        Row(
            modifier
                .fillMaxWidth()
                .fillMaxHeight(.2f)
                .padding(20.dp)
                .background(
                    MaterialTheme.colorScheme.primaryContainer,
                    MaterialTheme.shapes.small
                )
                .border(
                    2.dp,
                    MaterialTheme.colorScheme.onPrimaryContainer,
                    MaterialTheme.shapes.small),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.past_sessions),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 20.dp),
                textAlign = TextAlign.Center
            )
        }

        Row(
            modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(
                            MaterialTheme.colorScheme.tertiaryContainer,
                            MaterialTheme.shapes.medium)
                        .border(
                            2.dp,
                            MaterialTheme.colorScheme.onTertiaryContainer,
                            MaterialTheme.shapes.medium
                        )
                ) {
                    if (sessionUiState.sessions.size == 0) {
                        Text(text = "No Past Sessions :(", style = MaterialTheme.typography.headlineSmall)
                    } else {
                        LazyColumn() {
                            items(sessionUiState.sessions.size) {
                                PastSessionCard(session = sessionUiState.sessions[it], Modifier)
                            }
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
        PastSessionsScreen(sessionUiState, Modifier.background(MaterialTheme.colorScheme.background))
    }
}