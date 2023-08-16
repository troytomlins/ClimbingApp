package com.example.climbingapp.init

import com.example.climbingapp.model.climbs.Gym
import com.example.climbingapp.model.climbs.Location
import com.example.climbingapp.model.climbs.Rope
import com.example.climbingapp.model.climbs.Route
import kotlin.random.Random

/**
 * Initialising data for testing before data persistence
 */
class ClimbInit {

    /**
     * Sets up a gym and returns it
     * with 3 ropes with 3 routes each
     * @return Gym
     */
    fun setUpGymData(): Gym {
        val location = Location(
            city = "Christchurch",
            country = "New Zealand"
        )
        val gym = Gym(
            location = location,
            name = "Y Adventure Centre"
        )

        gym.addRopeToGym(setUpRope("1"))
        gym.addRopeToGym(setUpRope("2"))
        gym.addRopeToGym(setUpRope("3"))
        return gym
    }

    /**
     * Sets up a rope with a given name and returns it
     * with 3 climbs of semi-random grades
     * @param name name of rope
     * @return Rope
     */
    private fun setUpRope(name: String): Rope {
        val rope = Rope(name)
        val grades = mutableListOf(Random.nextInt(11,16), Random.nextInt(17,21), Random.nextInt(22,28))
        var route1 = Route(
            name = "Easy Climb",
            grade = grades[0],
            color = "Blue",
            gradeComplete = true
        )
        var route2 = Route(
            name = "Medium Climb",
            grade = grades[1],
            color = "Yellow",
            gradeComplete = true
        )
        var route3 = Route(
            name = "Hard Climb",
            grade = grades[2],
            color = "Black/Gray",
            gradeComplete = true
        )
        rope.addRouteToRope(route1)
        rope.addRouteToRope(route2)
        rope.addRouteToRope(route3)
        return rope
    }
}