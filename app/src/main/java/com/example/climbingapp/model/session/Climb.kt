package com.example.climbingapp.model.session

import com.example.climbingapp.model.climbs.Rope
import com.example.climbingapp.model.climbs.Route

/**
 * Model Data class for Climb
 * Data of climb to be recorded in Session
 * @param rope Rope in gym climb is on
 * @param route route to record
 * @param sent T/F whether the user completed the climb
 */
data class Climb(
    val rope: Rope,
    val route: Route,
    val sent: Boolean
) {
    fun display(): String {
        return "Rope: ${rope.name} \nColor: ${route.color} \nGrade: ${route.grade} \nSent: ${sent}"
    }
}