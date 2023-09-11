package com.example.climbingapp.ui.session

/**
 * Session Ui State
 */
data class SessionUiState(
    var attempted: Int = 0,
    var completed: Int = 0,
    var highestGrade: Int = 0,
    var averageGrade: Int = 0,
    var lastClimb: Climb? = null
)