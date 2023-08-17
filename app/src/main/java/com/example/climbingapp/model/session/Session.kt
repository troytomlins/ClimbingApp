package com.example.climbingapp.model.session

import com.example.climbingapp.model.climbs.Gym
import java.util.Date

private val days = mutableListOf<String>(
    "Mon",
    "Tue",
    "Wed",
    "Thur",
    "Fri",
    "Sat",
    "Sun"
)
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
    private var date: Date = Date()

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

    fun getSessionDate(): Date {
        return date
    }

    fun getSessionDateString(): String {
        var time: String = if (date.minutes < 10) {
            "${date.hours}:0${date.minutes}"
        } else {
            "${date.hours}:${date.minutes}"
        }
        return "$time ${days[date.day-1]}\n${date.date}/${date.month+1}"
    }
}