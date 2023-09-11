package com.example.climbingapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.climbingapp.model.climbs.Location

/**
 * Entity class for Gyms
 */
@Entity(tableName = "gym")
class Gym(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var location: Location,
    var name: String,
    var gymCode: String
)
