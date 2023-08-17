package com.example.climbingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.climbingapp.ui.theme.ClimbingAppTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.climbingapp.init.ClimbInit
import com.example.climbingapp.model.session.Session
import com.example.climbingapp.ui.session.CurrentSessionScreen
import com.example.climbingapp.ui.session.StartSessionScreen
import com.example.climbingapp.viewmodel.ProfileViewModel
import com.example.climbingapp.viewmodel.SessionViewModel


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

    Scaffold(
        topBar = {
            ClimbingAppBar()
        }
    ) { contentPadding ->
        val sessionUiState by sessionViewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = ClimbingAppScreen.StartSession.name,
            modifier = Modifier.padding(contentPadding)
        ) {
            // Start Session Screen DEFAULT
            composable(route = ClimbingAppScreen.StartSession.name) {
                StartSessionScreen(onStart = {
                    val newSession = Session(ClimbInit().setUpGymData())
                    sessionViewModel.setUpSession(newSession)
                    navController.navigate(route = ClimbingAppScreen.CurrentSession.name)
                })
            }

            // Current Session Screen
            composable(route = ClimbingAppScreen.CurrentSession.name) {
                CurrentSessionScreen(
                    sessionUiState = sessionUiState,
                    addClimb = {
                        navController.navigate(route = ClimbingAppScreen.AddClimb.name)
                    })
            }

            // Add new Climb Screen
            composable(route = ClimbingAppScreen.AddClimb.name) {

            }

            // Past Sessions Screen
            composable(route = ClimbingAppScreen.PastSessions.name) {

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

@Composable
fun ClimbingAppBar(modifier: Modifier = Modifier) {

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ClimbingAppTheme {
        Greeting("Android")
    }
}