package com.example.climbingapp.model.climbs

data class Route(
    var name: String = "Unnamed Route",
    var grade: Int,
    val color: String,
    var gradeComplete: Boolean = true
)
