package com.example.climbingapp.model.climbs

/**
 * Data model class for Route
 * @param name Name of Route (Optional)
 * @param grade Grade of Route
 * @param color Color of Route
 * @param gradeComplete indicates whether grading has been completed (Optional)
 */
data class Route(
    var name: String = "Unnamed Route",
    var grade: Int,
    val color: String,
    var gradeComplete: Boolean = true
)
