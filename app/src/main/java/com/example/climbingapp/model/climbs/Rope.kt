package com.example.climbingapp.model.climbs

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