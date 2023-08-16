package com.example.climbingapp.model.climbs

class Gym(
    var location: Location,
    var name: String
) {

    private val ropes: MutableList<Rope> = mutableListOf()

    fun addRopeToGym(rope: Rope) {
        ropes.add(rope)
    }

    fun removeRopeFromGym(rope: Rope) {
        ropes.remove(rope)
    }

    fun getRopes() : MutableList<Rope> {
        return ropes
    }

    fun updateGym(newLocation: Location, newName: String) {
        location = newLocation
        name = newName
    }
}
