package com.example.climbingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.climbingapp.ui.theme.ClimbingAppTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.climbingapp.init.ClimbInit
import com.example.climbingapp.model.session.Session
import com.example.climbingapp.ui.pastsession.PastSessionsScreen
import com.example.climbingapp.ui.session.CurrentSessionScreen
import com.example.climbingapp.ui.session.StartSessionScreen
import com.example.climbingapp.viewmodel.PastSessionViewModel
import com.example.climbingapp.viewmodel.ProfileViewModel
import com.example.climbingapp.viewmodel.SessionViewModel

val TESTING = true

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClimbingAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ClimbingApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClimbingApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {

    val sessionViewModel: SessionViewModel = viewModel()
    val profileViewModel: ProfileViewModel = viewModel()
    val pastSessionViewModel: PastSessionViewModel = viewModel()

    if (TESTING) {
        pastSessionViewModel.setUpTestData()
    }

    Scaffold(
        topBar = {
            ClimbingAppBar(modifier)
        },
        bottomBar = {
            ClimbingAppNavBar(
                onNavListPress = {
                    navController.navigate(route = ClimbingAppScreen.PastSessions.name)
                },
                onNavHomePress = {
                    if (sessionViewModel.isActive()) {
                        navController.navigate(route = ClimbingAppScreen.CurrentSession.name)
                    } else {
                        navController.navigate(route = ClimbingAppScreen.StartSession.name)
                    }
                },
                onNavProfilePress = {
                    navController.navigate(route = ClimbingAppScreen.Profile.name)
                },
                modifier = modifier
            )
        }
    ) { contentPadding ->
        val sessionUiState by sessionViewModel.uiState.collectAsState()
        val pastSessionUiState by pastSessionViewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = ClimbingAppScreen.StartSession.name,
            modifier = Modifier.padding(contentPadding)
        ) {
            // Start Session Screen DEFAULT
            composable(route = ClimbingAppScreen.StartSession.name) {
                StartSessionScreen(
                    onStart = {
                        var newSession: Session = if (TESTING) {
                            ClimbInit().setUpSession() // full session
                        } else {
                            Session(ClimbInit().setUpGymData()) // empty session
                        }
                        sessionViewModel.setUpSession(newSession)
                        navController.navigate(route = ClimbingAppScreen.CurrentSession.name)
                    }
                )
            }

            // Current Session Screen
            composable(route = ClimbingAppScreen.CurrentSession.name) {
                CurrentSessionScreen(
                    sessionUiState = sessionUiState,
                    addClimb = {
                        navController.navigate(route = ClimbingAppScreen.AddClimb.name)
                    },
                    endSession = {
                        sessionViewModel.endSession()
                        navController.navigate(route = ClimbingAppScreen.StartSession.name)
                    })
            }


            // Add new Climb Screen
            composable(route = ClimbingAppScreen.AddClimb.name) {

            }

            // Past Sessions Screen
            composable(route = ClimbingAppScreen.PastSessions.name) {
                PastSessionsScreen(
                    sessionUiState = pastSessionUiState
                )
            }

            // Display Completed(past) Session
            composable(route = ClimbingAppScreen.CompletedSession.name) {

            }

            // User Profile
            composable(route = ClimbingAppScreen.Profile.name) {

            }

            // Gym Profile
            composable(route = ClimbingAppScreen.GymProfile.name) {

            }
        }
    }

}

enum class ClimbingAppScreen(@StringRes val title: Int) {
    StartSession(title = R.string.start_session),
    CurrentSession(title = R.string.current_session),
    AddClimb(title = R.string.add_climb),
    PastSessions(title = R.string.past_sessions),
    CompletedSession(title = R.string.completed_session),
    Profile(title = R.string.user_profile),
    GymProfile(title = R.string.gym_profile)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClimbingAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineMedium)
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
        modifier = modifier.background(MaterialTheme.colorScheme.primaryContainer)
    )
}

@Composable
fun ClimbingAppNavBar(
    onNavListPress: () -> Unit = {},
    onNavHomePress: () -> Unit = {},
    onNavProfilePress:() -> Unit = {},
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier.fillMaxWidth(),
    ) {
        Row(
            Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onNavListPress,
                Modifier
                    .fillMaxWidth(.33f)
                    .fillMaxHeight()) {
                Icon(Icons.Filled.List, contentDescription = null, Modifier.padding(4.dp))
            }
            IconButton(onClick = onNavHomePress,
                Modifier
                    .fillMaxWidth(.5f)
                    .fillMaxHeight()) {
                Icon(Icons.Filled.Home, contentDescription = null, Modifier.padding(4.dp))
            }
            IconButton(onClick = onNavProfilePress,
                Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight()) {
                Icon(Icons.Filled.Person, contentDescription = null, Modifier.padding(4.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClimbingAppPreview() {
    ClimbingAppTheme {
        ClimbingApp()
    }
}