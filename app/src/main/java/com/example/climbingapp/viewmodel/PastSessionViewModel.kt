package com.example.climbingapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.climbingapp.init.ClimbInit
import com.example.climbingapp.ui.pastsession.PastSessionUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Calendar

class PastSessionViewModel: ViewModel() {



    private val _uiState = MutableStateFlow(PastSessionUiState())
    val uiState: StateFlow<PastSessionUiState> = _uiState.asStateFlow()
    fun setUpTestData() {
        val climbInit = ClimbInit()
        var date = Calendar.getInstance()

        date.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH)-2)
        val session1 = climbInit.setUpSession()
        session1.setSessionDate(date)

        date = Calendar.getInstance()
        date.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH)-4)
        val session2 = climbInit.setUpSession()
        session2.setSessionDate(date)

        date = Calendar.getInstance()
        date.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH)-10)
        val session3 = climbInit.setUpSession()
        session3.setSessionDate(date)

        uiState.value.sessions = mutableListOf(session1,session2,session3)
    }
}