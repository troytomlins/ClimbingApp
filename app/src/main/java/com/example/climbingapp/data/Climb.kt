package com.example.climbingapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity data class for climbs in a session
 */
@Entity(tableName = "climb")
data class Climb(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val routeId: Int,
    val sent: Boolean = true
)