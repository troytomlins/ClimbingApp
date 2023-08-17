package com.example.climbingapp.model.session

import com.example.climbingapp.model.climbs.Gym
import java.util.Calendar

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
    private var date: Calendar = Calendar.getInstance()

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

    fun getSessionDate(): Calendar {
        return date
    }

    fun getSessionDateString(): String {
        var time: String = if (date.get(Calendar.MINUTE) < 10) {
            "${date.get(Calendar.HOUR_OF_DAY)}:0${date.get(Calendar.MINUTE)}"
        } else {
            "${date.get(Calendar.HOUR_OF_DAY)}:${date.get(Calendar.MINUTE)}"
        }
        return "$time ${days[date.get(Calendar.DAY_OF_WEEK)]}\n${date.get(Calendar.DAY_OF_MONTH)}/${date.get(Calendar.MONTH)}"
    }

    fun setSessionDate(newDate: Calendar) {
        date = newDate
    }
}