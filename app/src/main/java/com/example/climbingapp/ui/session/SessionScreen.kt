package com.example.climbingapp.ui.session

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.climbingapp.ui.AppViewModelProvider
import com.example.climbingapp.ui.theme.ClimbingAppTheme
import com.example.climbingapp.viewmodel.PastSessionsViewModel
import com.example.climbingapp.viewmodel.SessionViewModel

/**
 * Current Session Screen
 */
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SessionScreen(
    addClimb: () -> Unit,
    leaveSession: () -> Unit,
    modifier: Modifier = Modifier,
    active: Boolean = false,
    viewModel: SessionViewModel = viewModel(factory = AppViewModelProvider.Factory),
    ) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SessionDisplay(viewModel.uiState.value)
                if (viewModel.uiState.value.lastClimb != null) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "\nLast Climb: \n",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = viewModel.uiState.value.lastClimb!!.route.name,
                            textAlign = TextAlign.Left
                        )
                        Column {
                            Text(
                                text = "Color: ${viewModel.uiState.value.lastClimb!!.route.color}\n",
                                textAlign = TextAlign.Left
                            )
                            Text(
                                text = "Grade: ${viewModel.uiState.value.lastClimb!!.route.grade}\n",
                                textAlign = TextAlign.Left
                            )
                        }
                        Text(
                            text = if (viewModel.uiState.value.lastClimb!!.sent) {
                                "Completed"
                            } else {
                                "Incomplete"
                            }
                        )
                    }
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "View completed climbs")
                }
            }
        }
        if (active) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            ) {
                OutlinedButton(onClick = {
                    viewModel.endSession()
                    leaveSession()
                }) {
                    Text(text = "End Session")
                }
                Button(onClick = addClimb) {
                    Text(text = "Add Climb")
                }
            }
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
@Preview
fun CurrentSessionPreview() {
    ClimbingAppTheme {
        val session = ActiveSession()
        val sessionViewModel = SessionViewModel()
        sessionViewModel.setUpSession(session)
        SessionScreen({}, {}, Modifier.background(MaterialTheme.colorScheme.background), true)
    }
}

@Composable
@Preview
fun PastSessionPreview() {
    ClimbingAppTheme {
        val session = ActiveSession()
        val sessionViewModel = SessionViewModel()
        sessionViewModel.setUpSession(session)
        SessionScreen({}, {}, Modifier.background(MaterialTheme.colorScheme.background), false)
    }
}