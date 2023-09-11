package com.example.climbingapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.climbingapp.data.Gym
import com.example.climbingapp.model.climbs.Location
import com.example.climbingapp.ui.session.ActiveSession
import com.example.climbingapp.ui.session.Climb
import com.example.climbingapp.ui.session.SessionUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.roundToInt

class SessionViewModel: ViewModel() {
    private var gym = Gym(
        location = Location("CHCH","NZ"),
        name = "Y Adventure Centre",
        gymCode = "YAC"
    )
    private var activeSession = ActiveSession()

    private var active: Boolean = false

    private val _uiState = MutableStateFlow(SessionUiState())
    val uiState: StateFlow<SessionUiState> = _uiState.asStateFlow()

    /**
     * Sets up active session
     */
    fun setUpSession(session: ActiveSession): SessionViewModel {
        activeSession = session
        gym = gym
        resetUi()
        active = true
        return this
    }

    /**
     * Resets the Session Ui values
     */
    private fun resetUi() {
        // Set Ui State
        uiState.value.averageGrade = calculateAverageGrade()
        uiState.value.attempted = getNumClimbsAttempted()
        uiState.value.completed = getNumClimbsCompleted()
        uiState.value.highestGrade = getHighestGradeInSession()
        uiState.value.lastClimb = getLastClimb()
    }

    /**
     * Gets and returns the highest grade completed in the session
     * @return Highest grade in session
     */
    private fun getHighestGradeInSession(): Int {
        return activeSession.getHighestGrade()
    }

    /**
     * Gets and returns number of climbs attempted in current session
     * @return No. of Climbs attempted
     */
    private fun getNumClimbsAttempted(): Int {
        return activeSession.getClimbs().size
    }

    /**
     * Gets and returns the number of climbs completed
     * @return No. of climbs completed
     */
    private fun getNumClimbsCompleted(): Int {
        var count = 0
        activeSession.getClimbs().forEach {
            if (it.sent) {
                count += 1
            }
        }
        return count
    }

    /**
     * Calculates and returns the average grade in session
     * rounded to nearest Int
     * @return Average grade
     */
    private fun calculateAverageGrade(): Int {
        if (activeSession.getClimbs().size == 0) {
            return 0
        }
        var count = 0.00
        var total = 0.00
        activeSession.getClimbs().forEach {
            if (it.sent) {
                count += 1
                total += it.route.grade
            }
        }
        return (total / count).roundToInt()
    }

    private fun getLastClimb() : Climb {
        return activeSession.getClimbs().last()
    }

    /**
     * Sets the current session as inactive
     * TODO: send session data
     */
    fun endSession() {
        active = false
    }

    fun isActive(): Boolean {
        return active
    }
}