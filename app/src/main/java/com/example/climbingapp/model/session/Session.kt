package com.example.climbingapp.model.session

import com.example.climbingapp.model.climbs.Gym

/**
 * Model class for Session
 * Tracks climbs in each session at inputted gym
 * @param gym Gym session is taking place
 */
class Session(
    val gym: Gym
) {
    private var climbs = mutableListOf<Climb>()
    private var highestGrade: Int = 0

    fun addClimbToSession(climb: Climb) {
        climbs.add(climb)
        if (climb.sent && climb.route.grade > highestGrade) {
            highestGrade = climb.route.grade
        }
    }

    fun getClimbsInSession(): MutableList<Climb> {
        return climbs
    }

    fun getHighestGradeInSession(): Int {
        return highestGrade
    }
}