package com.example.climbingapp.ui.pastsession

import com.example.climbingapp.model.session.Climb
import com.example.climbingapp.model.session.Session
import com.example.climbingapp.ui.session.SessionUiState
import com.example.climbingapp.viewmodel.SessionViewModel

data class PastSessionUiState(
    var sessions: MutableList<Session> = mutableListOf(),
    var activeSession: Session? = null,
    var activeClimb: Climb? = null
) {
    /**
     * Converts active session into SessionUiState
     */
    fun convertToSessionUiState(): SessionUiState? {
        return if (activeSession != null) {
            val viewModel = SessionViewModel()
            viewModel.setUpSession(activeSession!!)
            viewModel.uiState.value
        } else {
            null
        }
    }

    /**
     * Sets the active session
     * @param session Session to set as active
     */
    fun setActiveSession(session: Session) {
        activeSession = session
    }
}