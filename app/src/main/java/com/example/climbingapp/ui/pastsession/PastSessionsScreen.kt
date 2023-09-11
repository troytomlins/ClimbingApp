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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.climbingapp.R
import com.example.climbingapp.ScreenTitle
import com.example.climbingapp.data.Gym
import com.example.climbingapp.data.session.Session
import com.example.climbingapp.model.climbs.Location
import com.example.climbingapp.ui.AppViewModelProvider
import com.example.climbingapp.ui.theme.ClimbingAppTheme
import com.example.climbingapp.viewmodel.PastSessionsViewModel
import java.util.Calendar
import kotlin.random.Random

/**
 * Display for showing past sessions as scrolling list
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PastSessionsScreen(
    viewModel: PastSessionsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {

    val pastSessionsUiState by viewModel.pastSessionsUiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

//    Column(
//        modifier.fillMaxSize()
//    ) {
//        when (uiStatus) {
//            is PastSessionUiStatus.Loading -> null // TODO: Add loading screen
//            is PastSessionUiStatus.Success ->
//                PastSessionsScreenContent(
//                    uiState,
//                    modifier
//                )
//            is PastSessionUiStatus.Error -> null // TODO: Add error screen
//        }
//    }
    PastSessionsScreenContent(sessionList = pastSessionsUiState.sessionList)
}

@Composable
fun PastSessionsScreenContent(
    sessionList: List<Session>,
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
                if (sessionList.isEmpty()) {
                    Text(
                        text = "No Past Sessions :(",
                        style = MaterialTheme.typography.headlineSmall
                    )
                } else {
                    LazyColumn() {
                        items(sessionList.size) {
                            PastSessionCard(session = sessionList[it], Modifier)
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
            Text(text = "${session.date.date}/${session.date.month}")
            Text(text = "Climbs: ${session.climbIds.size}\nHighest: ${session.highestGrade}")
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
        val sessions = testSessionsData()
        PastSessionsScreenContent(sessions, Modifier.background(MaterialTheme.colorScheme.background))
    }
}

fun testSessionsData(): MutableList<Session> {
    val gym = Gym(location = Location("CHCH", "NZ"), name = "Y Adventure Centre", gymCode = "YAC")
    val sessions: MutableList<Session> = mutableListOf()
    var i = 0
    val climbIds: MutableList<Int> = mutableListOf()
    while (i < 5) {
        //climbs
        var j = 0
        while (j < Random.nextInt(3, 5)) {
            climbIds += Random.nextInt()
            j += 1
        }
        val highestGrade = Random.nextInt(15, 25)
        sessions.add(Session(gymId = gym.id, climbIds = climbIds, highestGrade = highestGrade))
        i += 1
    }
    return sessions
}