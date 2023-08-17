package com.example.climbingapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.climbingapp.init.ClimbInit
import com.example.climbingapp.model.session.Climb
import com.example.climbingapp.model.session.Session
import com.example.climbingapp.ui.session.SessionUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.roundToInt

class SessionViewModel: ViewModel() {
    private var gym = ClimbInit().setUpGymData()
    private var activeSession = Session(gym)

    private val _uiState = MutableStateFlow(SessionUiState())
    val uiState: StateFlow<SessionUiState> = _uiState.asStateFlow()

    /**
     * Sets up active session
     */
    fun setUpSession(session: Session): SessionViewModel {
        activeSession = session
        gym = session.gym
        resetUi()
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
        uiState.value.lastClimb = getPreviousClimbDisplayString()
    }

    /**
     * Add a new climb to session and updates the UI
     */
    fun addClimbToSession(climb: Climb) {
        activeSession.addClimbToSession(climb)
        _uiState.value.attempted += 1
        if (climb.sent) {
            _uiState.value.completed += 1
            _uiState.value.averageGrade = calculateAverageGrade()
            _uiState.value.highestGrade = activeSession.getHighestGradeInSession()
        }
        _uiState.value.lastClimb = climb.display()
    }

    /**
     * Gets and returns the highest grade completed in the session
     * @return Highest grade in session
     */
    private fun getHighestGradeInSession(): Int {
        return activeSession.getHighestGradeInSession()
    }

    /**
     * Gets and returns number of climbs attempted in current session
     * @return No. of Climbs attempted
     */
    private fun getNumClimbsAttempted(): Int {
        return activeSession.getClimbsInSession().size
    }

    /**
     * Gets and returns the number of climbs completed
     * @return No. of climbs completed
     */
    private fun getNumClimbsCompleted(): Int {
        var count = 0
        activeSession.getClimbsInSession().forEach {
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
        if (activeSession.getClimbsInSession().size == 0) {
            return 0
        }
        var count = 0.00
        var total = 0.00
        activeSession.getClimbsInSession().forEach {
            if (it.sent) {
                count += 1
                total += it.route.grade
            }
        }
        return (total / count).roundToInt()
    }

    private fun getPreviousClimbDisplayString(): String {
        if (activeSession.getClimbsInSession().size == 0) {
            return ""
        }
        return activeSession.getClimbsInSession().last().display()
    }
}