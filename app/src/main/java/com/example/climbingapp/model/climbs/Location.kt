package com.example.climbingapp.model.climbs

/**
 * Data model class for Location
 * contains overridden toString() method
 * @param city City gym is located
 * @param country Country gym is located
 */
data class Location(
    val city: String,
    val country: String
) {
    override fun toString(): String {
        return "${city}, ${country}"
    }
}