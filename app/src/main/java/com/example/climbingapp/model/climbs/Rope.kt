package com.example.climbingapp.model.climbs

// TODO: ropeName must be unique

/**
 * Model class for Rope
 * Act as a between stage for Route and Gym
 * @param ropeName Name of rope
 */
class Rope(ropeName: String) {
    val name = ropeName
    private var routes: MutableList<Route> = mutableListOf()

    fun addRouteToRope(route: Route) {
        routes.add(route)
    }

    fun removeRouteFromRope(route: Route) {
        routes.remove(route)
    }

    fun getRoutes(): MutableList<Route> {
        return routes
    }
}