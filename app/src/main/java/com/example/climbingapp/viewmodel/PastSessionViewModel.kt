package com.example.climbingapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.climbingapp.init.ClimbInit
import com.example.climbingapp.model.session.Session
import com.example.climbingapp.network.ClimbApi
import com.example.climbingapp.ui.pastsession.PastSessionUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.Calendar

sealed interface PastSessionUiStatus {
    data class Success(val pastSession: Session) : PastSessionUiStatus
    object Error : PastSessionUiStatus
    object Loading : PastSessionUiStatus
}

class PastSessionViewModel: ViewModel() {

    var uiStatus: PastSessionUiStatus by mutableStateOf(PastSessionUiStatus.Loading)
        private set

    private val _uiState = MutableStateFlow(PastSessionUiState())
    val uiState: StateFlow<PastSessionUiState> = _uiState.asStateFlow()

    /**
     * Calls Api to get past session data
     */
    fun getPastSessionData() {
        viewModelScope.launch {
            uiStatus = try {
                val pastSessionData = ClimbApi.retrofitService.getPastSession("")
                PastSessionUiStatus.Success(pastSessionData)
            } catch (e: IOException) {
                PastSessionUiStatus.Error
            }
        }
    }

    // Test Data
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