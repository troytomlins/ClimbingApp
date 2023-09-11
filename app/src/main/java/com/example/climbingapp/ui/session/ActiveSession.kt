package com.example.climbingapp.ui.session

class ActiveSession() {
    private var climbs : MutableList<Climb> = mutableListOf()
    fun addClimb(climb: Climb) {
        climbs.add(climb)
    }

    fun getHighestGrade(): Int {
        var highest = 0
        for (climb in climbs) {
            if (climb.route.grade > highest && climb.sent) {
                highest = climb.route.grade
            }
        }
        return highest
    }

    fun getClimbs(): MutableList<Climb> {
        return climbs
    }
}

data class Climb(
    val route: Route,
    val sent: Boolean = true
)

data class Route(
    val ropeNo: Int = 0,
    val name: String = "Unnamed Route",
    val grade: Int = 0,
    val color: String = "",
    val gradeComplete: Boolean = true
)