package com.example.climbingapp.ui.pastsession

import com.example.climbingapp.model.session.Climb
import com.example.climbingapp.model.session.Session
import com.example.climbingapp.ui.session.SessionUiState
import com.example.climbingapp.viewmodel.SessionViewModel

data class PastSessionUiState(
    var sessions: MutableList<Session> = mutableListOf(),
    val activeSession: Session? = null,
    val activeClimb: Climb? = null
) {
    fun convertToSessionUiState(): SessionUiState? {
        if (activeSession != null) {
            val viewModel = SessionViewModel()
            viewModel.setUpSession(activeSession)
            return viewModel.uiState.value
        } else {
            return null
        }
    }
}