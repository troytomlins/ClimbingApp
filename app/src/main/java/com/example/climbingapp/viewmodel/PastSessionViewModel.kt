package com.example.climbingapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.climbingapp.init.ClimbInit
import com.example.climbingapp.ui.pastsession.PastSessionUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PastSessionViewModel: ViewModel() {



    private val _uiState = MutableStateFlow(PastSessionUiState())
    val uiState: StateFlow<PastSessionUiState> = _uiState.asStateFlow()
    fun setUpTestData() {
        val climbInit = ClimbInit()
        val session1 = climbInit.setUpSession()
        val session2 = climbInit.setUpSession()
        val session3 = climbInit.setUpSession()

        uiState.value.sessions = mutableListOf(session1,session2,session3)
    }
}