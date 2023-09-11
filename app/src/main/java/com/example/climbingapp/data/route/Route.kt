package com.example.climbingapp.data.route

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity class for Routes
 */
@Entity
data class Route(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val gymId: Int,
    val ropeNo: Int,
    val name: String = "Unnamed Route",
    val grade: Int,
    val color: String,
    val gradeComplete: Boolean = true
)
