package com.example.climbingapp.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.climbingapp.ClimbingApplication
import com.example.climbingapp.viewmodel.PastSessionsViewModel
import com.example.climbingapp.viewmodel.ProfileViewModel
import com.example.climbingapp.viewmodel.SessionViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {

        // Initializer for PastSessionViewModel
        initializer {
            PastSessionsViewModel(climbingApplication().container.sessionRepository)
        }

        // Initializer for ProfileViewModel
        initializer {
            ProfileViewModel()
        }

        // Initializer for SessionViewModel
        initializer {
            SessionViewModel()
        }

    }
}

fun CreationExtras.climbingApplication(): ClimbingApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ClimbingApplication)