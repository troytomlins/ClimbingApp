package com.example.climbingapp.model.session

import com.example.climbingapp.model.climbs.Gym

class Session(
    val gym: Gym
) {
    private var climbs = mutableListOf<Climb>()

    fun addClimbToSession(climb: Climb) {
        climbs.add(climb)
    }

    fun getClimbsInSession(): MutableList<Climb> {
        return climbs
    }
}