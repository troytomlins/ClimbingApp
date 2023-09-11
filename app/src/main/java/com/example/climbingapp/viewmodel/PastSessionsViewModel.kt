package com.example.climbingapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.climbingapp.data.session.Session
import com.example.climbingapp.data.session.SessionRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

sealed interface PastSessionUiStatus {
//    data class Success(val pastSession: Session) : PastSessionUiStatus
//    object Error : PastSessionUiStatus
//    object Loading : PastSessionUiStatus
}

class PastSessionsViewModel(sessionRepository: SessionRepository): ViewModel() {

//    var uiStatus: PastSessionUiStatus by mutableStateOf(PastSessionUiStatus.Loading)
//        private set
//
//    private val _uiState = MutableStateFlow(PastSessionUiState())
//    val uiState: StateFlow<PastSessionUiState> = _uiState.asStateFlow()


    val pastSessionsUiState: StateFlow<PastSessionsUiState> =
        sessionRepository.getAllSessions().map { PastSessionsUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = PastSessionsUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }



//    /**
//     * Calls Api to get past session data
//     */
//    fun getPastSessionData() {
//        viewModelScope.launch {
//            uiStatus = try {
//                val pastSessionData = ClimbApi.retrofitService.getPastSession("")
//                PastSessionUiStatus.Success(pastSessionData)
//            } catch (e: IOException) {
//                PastSessionUiStatus.Error
//            }
//        }
//    }

    // Test Data
//    fun setUpTestData() {
//        val climbInit = ClimbInit()
//        var date = Calendar.getInstance()
//
//        date.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH)-2)
//        val session1 = climbInit.setUpSession()
//        session1.setSessionDate(date)
//
//        date = Calendar.getInstance()
//        date.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH)-4)
//        val session2 = climbInit.setUpSession()
//        session2.setSessionDate(date)
//
//        date = Calendar.getInstance()
//        date.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH)-10)
//        val session3 = climbInit.setUpSession()
//        session3.setSessionDate(date)
//
//        uiState.value.sessions = mutableListOf(session1,session2,session3)
//    }
}

/**
 * Ui State for [PastSessionsScreen]
 */
data class PastSessionsUiState(val sessionList: List<Session> = listOf())