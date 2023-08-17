package com.example.climbingapp.ui.pastsession

import com.example.climbingapp.model.session.Climb
import com.example.climbingapp.model.session.Session

data class PastSessionUiState(
    var sessions: MutableList<Session> = mutableListOf(),
    val activeSession: Session? = null,
    val activeClimb: Climb? = null
)